import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphonesByBrandService } from "./smartphones-by-brand.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";

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

  // tslint:disable-next-line:max-line-length
  constructor(
    private smartphonesByBrandService: SmartphonesByBrandService,
    private router: Router
  ) {}

  ngOnInit(): void {
    AOS.init({});

    this.brandName = localStorage.getItem("brandName");
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
    this.smartphonesByBrandService.getDetails().subscribe(data => {
      if (!data) {
        this.brandObject = null;
        this.smartphonesByBrand = null;
      } else {
        this.brandObject = data;
        this.smartphonesByBrand = this.brandObject.smartphones;
      }
    });
  }

  showSmartphoneDetails(id: number) {
    this.smartphonesByBrandService.setId(id);
    this.router.navigate(["/details"]);
  }
}
