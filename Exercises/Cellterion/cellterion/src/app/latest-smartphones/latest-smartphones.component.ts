import { Component, OnDestroy, OnInit } from "@angular/core";
import { LatestSmartphonesService } from "./latest-smartphones.service";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import { filter, takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";
import * as AOS from "aos";

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
    this.latestSmartphonesService.getLatest().subscribe(data => {
      this.latestSmartphones = data;
    });
  }

  showSmartphoneDetails(id: number) {
    this.latestSmartphonesService.setId(id);
    this.router.navigate(["/details"]);
  }
}
