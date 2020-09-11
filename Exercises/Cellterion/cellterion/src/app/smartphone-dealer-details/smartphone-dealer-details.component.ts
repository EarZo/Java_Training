import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphoneDealerDetailsService } from "./smartphone-dealer-details.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";

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
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    AOS.init({});
    this.fetchData();
    this.titleService.setTitle(this.smartphoneDealer + " Details");

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
    this.smartphoneDealerDetailsService.getSmartphoneDealerDetails().subscribe(
      response => {
        this.smartphoneDealer = response;
        this.dealerAddresses = this.smartphoneDealer.addresses;
        this.dealerTelephoneNumbers = this.smartphoneDealer.telephoneList;
      },
      (error: AppError) => {
        this.smartphoneDealer = null;
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
}
