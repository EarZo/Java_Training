import { Component, OnInit } from "@angular/core";
import { SearchService } from "./search.service";
import { Router } from "@angular/router";

declare var $: any;

@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"]
})
export class SearchComponent implements OnInit {
  budget;

  constructor(private searchService: SearchService, private router: Router) {}

  ngOnInit(): void {
    $("#inpt_search").on("focus", function() {
      $(this)
        .parent("label")
        .addClass("active");
    });

    $("#inpt_search").on("blur", function() {
      if ($(this).val().length === 0) {
        $(this)
          .parent("label")
          .removeClass("active");
      }
    });

    $("#inpt_search").on("paste", function(e) {
      e.preventDefault();
    });
  }

  onKeyUp() {
    // console.log(this.budget);
    this.showBudgetResults(this.budget);
  }

  preventInput($event) {
    // prevent: "e", "=", ",", "-", "."
    if ([69, 187, 188, 189, 190, 107, 109, 110].includes($event.keyCode)) {
      $event.preventDefault();
    }
  }

  showBudgetResults(budget: number) {
    this.searchService.setBudget(budget);
    this.router.navigate(["/budget"]);
  }
}
