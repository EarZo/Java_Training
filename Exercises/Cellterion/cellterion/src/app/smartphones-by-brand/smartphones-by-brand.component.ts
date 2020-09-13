import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphonesByBrandService } from "./smartphones-by-brand.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";
import { ToastrService } from "ngx-toastr";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";

@Component({
  selector: "app-smartphones-by-brand",
  templateUrl: "./smartphones-by-brand.component.html",
  styleUrls: ["./smartphones-by-brand.component.css"]
})
export class SmartphonesByBrandComponent implements OnInit, OnDestroy {
  public destroyed = new Subject<any>();
  brandObject: any;
  smartphonesByBrand: Array<any>;
  brandName: any;

  constructor(
    private smartphonesByBrandService: SmartphonesByBrandService,
    private router: Router,
    private titleService: Title,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    AOS.init({});

    this.brandName = localStorage.getItem("brandName");
    this.titleService.setTitle(this.brandName);
    this.fetchData();

    this.router.events
      .pipe(
        filter((event: RouterEvent) => event instanceof NavigationEnd),
        takeUntil(this.destroyed)
      )
      .subscribe(() => {
        this.fetchData();
      });
  }

  ngOnDestroy(): void {
    this.destroyed.next();
    this.destroyed.complete();
  }

  fetchData() {
    this.smartphonesByBrandService.getDetails().subscribe(
      response => {
        this.brandObject = response;
        this.smartphonesByBrand = this.brandObject.smartphones;
      },
      (error: AppError) => {
        this.brandObject = null;
        if (error instanceof NotFoundError) {
          this.toastr.error(
            "Seems like our server's having some trouble! We'll fix it as soon as possible.",
            "Oops! It's Not You, It's Us!"
          );
          // console.log(error);
        } else {
          this.toastr.error(
            "An unexpected error occured!",
            "Oops! It's Not You, It's Us!"
          );
          // console.log(error);
        }
      }
    );
  }

  showSmartphoneDetails(id: number) {
    this.smartphonesByBrandService.setId(id);
    this.router.navigate(["/details"]);
  }
}
