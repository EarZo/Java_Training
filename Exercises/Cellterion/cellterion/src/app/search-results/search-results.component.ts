import { Component, OnInit, OnDestroy } from "@angular/core";
import { SearchResultsService } from "./search-results.service";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import * as AOS from "aos";
import { Subject } from "rxjs";

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
    private router: Router
  ) {}

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
    this.searchResultsService.getDetails().subscribe(data => {
      if (!data) {
        this.smartphones = null;
      } else {
        this.smartphones = data;
      }
    });
  }

  showSmartphoneDetails(id: number) {
    this.searchResultsService.setId(id);
    this.router.navigate(["/details"]);
  }
}
