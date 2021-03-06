import { Component, OnDestroy, OnInit } from "@angular/core";
import { OwlOptions } from "ngx-owl-carousel-o";
import { SmartphoneDetailsService } from "./smartphone-details.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { LatestSmartphonesService } from "../latest-smartphones/latest-smartphones.service";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-smartphone-details",
  templateUrl: "./smartphone-details.component.html",
  styleUrls: ["./smartphone-details.component.css"]
})
export class SmartphoneDetailsComponent implements OnInit, OnDestroy {
  public destroyed = new Subject<any>();
  smartphoneDetails: any;
  smartphoneCameras: Array<any>;
  smartphoneVideoCameras: Array<any>;
  smartphoneImages: Array<any>;
  smartphoneDealers: Array<any>;
  pixels: number;
  resolution: number;

  customOptions: OwlOptions = {
    center: false,
    lazyLoad: true,
    items: 1,
    loop: false,
    smartSpeed: 700,
    stagePadding: 15,
    margin: 20,
    autoplay: false,
    nav: true,
    autoplayHoverPause: true,
    navText: [
      '<span class="icon-chevron-left">',
      '<span class="icon-chevron-right">'
    ],
    responsive: {
      600: {
        margin: 20,
        items: 2
      },
      1000: {
        margin: 20,
        items: 3
      },
      1200: {
        margin: 20,
        items: 3
      }
    }
  };

  constructor(
    private smartphoneDetailsService: SmartphoneDetailsService,
    private latestSmartphonesService: LatestSmartphonesService,
    private router: Router,
    private titleService: Title,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    AOS.init({});
    this.fetchData();
    this.titleService.setTitle(
      this.smartphoneDetails.brandName + " " + this.smartphoneDetails.model
    );

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
    localStorage.removeItem("smartphoneId");
  }

  fetchData() {
    this.smartphoneDetailsService.getAll().subscribe(
      response => {
        this.smartphoneDetails = response;
        this.smartphoneCameras = this.smartphoneDetails.mainCameras;
        this.smartphoneVideoCameras = this.smartphoneDetails.videoCameras;
        this.smartphoneImages = this.smartphoneDetails.images;
        this.smartphoneDealers = this.smartphoneDetails.smartphoneDealers;
      },
      (error: AppError) => {
        this.smartphoneDetails = null;

        if (error instanceof NotFoundError) {
          this.toastr.error(
            "Seems like our server's having some trouble! We'll fix it as soon as possible.",
            "Oops! It's Not You, It's Us!"
          );
          console.log(error);
        } else throw error;
      }
    );
  }

  getMaxCameraPixels() {
    this.pixels = 0;
    this.smartphoneCameras.forEach(smartphoneCamera => {
      if (smartphoneCamera.pixels > this.pixels) {
        this.pixels = smartphoneCamera.pixels;
      }
    });

    return this.pixels;
  }

  getMaxVideoResolution() {
    this.resolution = 0;
    this.smartphoneVideoCameras.forEach(smartphoneVideoCamera => {
      if (smartphoneVideoCamera.resolution > this.resolution) {
        this.resolution = smartphoneVideoCamera.resolution;
      }
    });

    return this.resolution;
  }

  showSmartphoneDealerDetails(dealerName: any) {
    this.smartphoneDetailsService.setDealerName(dealerName);
    this.router.navigate(["/dealer"]);
  }
}
