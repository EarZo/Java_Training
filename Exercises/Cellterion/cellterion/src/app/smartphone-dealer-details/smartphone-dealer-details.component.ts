import { Component, OnDestroy, OnInit } from "@angular/core";
import { SmartphoneDealerDetailsService } from "./smartphone-dealer-details.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";
import { ToastrService } from "ngx-toastr";

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
    private titleService: Title,
    private toastr: ToastrService
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
}
