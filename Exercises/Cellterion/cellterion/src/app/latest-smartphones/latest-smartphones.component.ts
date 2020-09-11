import { NotFoundError } from "./../common/not-found-error";
import { AppError } from "./../common/app-error";
import { Component, OnDestroy, OnInit } from "@angular/core";
import { LatestSmartphonesService } from "./latest-smartphones.service";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import { filter, takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";
import * as AOS from "aos";
import { Title } from "@angular/platform-browser";

declare var $: any;

@Component({
  selector: "app-latest-smartphones",
  templateUrl: "./latest-smartphones.component.html",
  styleUrls: ["./latest-smartphones.component.css"]
})
export class LatestSmartphonesComponent implements OnInit, OnDestroy {
  currentYear: number = new Date().getFullYear();
  latestSmartphones: Array<any>;
  public destroyed = new Subject<any>();

  constructor(
    private latestSmartphonesService: LatestSmartphonesService,
    private router: Router,
    private titleService: Title
  ) {
    this.titleService.setTitle(this.currentYear + " Latest Smartphones");
  }

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
    this.latestSmartphonesService.getLatest().subscribe(
      response => {
        this.latestSmartphones = response;
      },
      (error: AppError) => {
        this.latestSmartphones = null;
        if (error instanceof NotFoundError) {
          console.log(
            "Oops! It's not you, it's us! Seems like our server's having some trouble! We'll fix it as soon as possible."
          );
        } else {
          alert("An unexpected error occured!");
          console.log(error);
        }
      }
    );
  }

  showSmartphoneDetails(id: number) {
    this.latestSmartphonesService.setId(id);
    this.router.navigate(["/details"]);
  }
}
