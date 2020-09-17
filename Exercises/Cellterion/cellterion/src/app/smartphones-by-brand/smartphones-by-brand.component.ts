import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphonesByBrandService } from "./smartphones-by-brand.service";
import { filter, takeUntil } from "rxjs/operators";
import {
  ActivatedRoute,
  NavigationEnd,
  Router,
  RouterEvent
} from "@angular/router";
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
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    AOS.init({});
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
    localStorage.removeItem("brandName");
  }

  fetchData() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.brandName = params.get("brandName");
      localStorage.setItem("brandName", this.brandName);
      this.titleService.setTitle(this.brandName + " Smartphones");
    });

    this.smartphonesByBrandService.getAll(this.brandName).subscribe(
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
        } else throw error;
      }
    );
  }

  showSmartphoneDetails(id: number) {
    this.smartphonesByBrandService.setId(id);
    this.router.navigate(["/details"]);
  }
}
