import { Component, OnInit, OnDestroy } from "@angular/core";
import { SearchResultsService } from "./search-results.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-search-results",
  templateUrl: "./search-results.component.html",
  styleUrls: ["./search-results.component.css"]
})
export class SearchResultsComponent implements OnInit, OnDestroy {
  public destroyed = new Subject<any>();
  smartphones: Array<any>;
  budget: any;

  constructor(
    private searchResultsService: SearchResultsService,
    private router: Router,
    private titleService: Title,
    private toastr: ToastrService
  ) {
    this.titleService.setTitle("Search Results");
  }

  ngOnInit(): void {
    AOS.init({});

    this.budget = localStorage.getItem("budget");
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
    this.searchResultsService.getDetails().subscribe(
      response => {
        this.smartphones = response;
      },
      (error: AppError) => {
        this.smartphones = null;
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

  showSmartphoneDetails(id: number) {
    this.searchResultsService.setId(id);
    this.router.navigate(["/details"]);
  }
}
