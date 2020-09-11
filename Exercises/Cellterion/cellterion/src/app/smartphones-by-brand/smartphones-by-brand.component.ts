import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphonesByBrandService } from "./smartphones-by-brand.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject, empty } from "rxjs";
import { Title } from "@angular/platform-browser";

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
    private titleService: Title
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
    this.smartphonesByBrandService.getDetails().subscribe(response => {
      if (empty) {
        this.brandObject = null;
        this.smartphonesByBrand = null;
      } else {
        this.brandObject = response;
        this.smartphonesByBrand = this.brandObject.smartphones;
      }
    });
  }

  showSmartphoneDetails(id: number) {
    this.smartphonesByBrandService.setId(id);
    this.router.navigate(["/details"]);
  }
}
