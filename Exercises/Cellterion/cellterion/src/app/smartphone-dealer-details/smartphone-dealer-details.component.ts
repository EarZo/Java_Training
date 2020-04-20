import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphoneDealerDetailsService } from "./smartphone-dealer-details.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";

@Component({
  selector: "app-smartphone-dealer-details",
  templateUrl: "./smartphone-dealer-details.component.html",
  styleUrls: ["./smartphone-dealer-details.component.css"]
})
export class SmartphoneDealerDetailsComponent implements OnInit, OnDestroy {
  public destroyed = new Subject<any>();
  smartphoneDealer: any;
  dealerAddresses: Array<any>;
  dealerTelephoneNumbers: Array<any>;

  constructor(
    private smartphoneDealerDetailsService: SmartphoneDealerDetailsService,
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
    this.smartphoneDealerDetailsService
      .getSmartphoneDealerDetails()
      .subscribe(data => {
        this.smartphoneDealer = data;
        this.dealerAddresses = this.smartphoneDealer.addresses;
        this.dealerTelephoneNumbers = this.smartphoneDealer.telephoneList;
      });
  }
}
