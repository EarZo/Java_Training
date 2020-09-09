import { Component, OnInit } from "@angular/core";
import { SearchService } from "./search.service";
import { Router } from "@angular/router";
import { FormGroup, FormControl, Validators } from "@angular/forms";

declare var $: any;

@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"]
})
export class SearchComponent implements OnInit {
  form = new FormGroup({
    search: new FormControl("", [
      Validators.required,
      Validators.min(10000),
      Validators.pattern("^[0-9]*$")
    ])
  });

  constructor(private searchService: SearchService, private router: Router) {}

  ngOnInit(): void {
    $("#inpt_search").on("focus", function() {
      $(this)
        .parent("label")
        .addClass("active");

      $(".speech-bubble").fadeIn("fast");
    });

    $("#inpt_search").on("blur", function() {
      if ($(this).val().length === 0) {
        $(this)
          .parent("label")
          .removeClass("active");
      }
      setTimeout(function() {
        $(".speech-bubble").fadeOut("fast");
      }, 1000);
    });
  }

  get search() {
    return this.form.get("search");
  }

  noPaste(event) {
    event.preventDefault();
  }

  numericOnly(event): boolean {
    // restrict E,e,+,-,. characters in search input
    const charCode = event.which ? event.which : event.keyCode;
    if (
      charCode == 101 ||
      charCode == 69 ||
      charCode == 43 ||
      charCode == 45 ||
      charCode == 46
    ) {
      return false;
    }
    return true;
  }

  showBudgetResults() {
    this.searchService.setBudget(this.form.value.search);
    this.router.navigate(["/budget"]);
  }
}
