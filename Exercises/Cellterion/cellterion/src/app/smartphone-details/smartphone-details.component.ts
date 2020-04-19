import { Component, OnDestroy, OnInit } from "@angular/core";
import { OwlOptions } from "ngx-owl-carousel-o";
import { SmartphoneDetailsService } from "./smartphone-details.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { LatestSmartphonesService } from "../latest-smartphones/latest-smartphones.service";
import { Subject } from "rxjs";

@Component({
  selector: "app-smartphone-details",
  templateUrl: "./smartphone-details.component.html",
  styleUrls: ["./smartphone-details.component.css"]
})
export class SmartphoneDetailsComponent implements OnInit, OnDestroy {
  private smartphoneId: number;
  public destroyed = new Subject<any>();
  smartphoneDetails: any;
  smartphoneCameras: Array<any>;
  smartphoneImages: Array<any>;
  camera: any;

  customOptions: OwlOptions = {
    center: false,
    items: 1,
    loop: true,
    smartSpeed: 700,
    stagePadding: 15,
    margin: 20,
    autoplay: true,
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

  // tslint:disable-next-line:max-line-length
  constructor(
    private smartphoneDetailsService: SmartphoneDetailsService,
    private latestSmartphonesService: LatestSmartphonesService,
    private router: Router
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
  }

  fetchData() {
    this.latestSmartphonesService.currentSmartphoneId.subscribe(data => {
      this.smartphoneId = data;
    });

    this.smartphoneDetailsService
      .getDetails(this.smartphoneId)
      .subscribe(data => {
        this.smartphoneDetails = data;
        this.smartphoneCameras = this.smartphoneDetails.mainCameras;
        this.smartphoneImages = this.smartphoneDetails.images;
      });
  }

  getCamera(mainCameraId) {
    return this.smartphoneCameras.find(x => x.mainCameraId === mainCameraId);
  }
}
