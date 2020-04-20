import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import { HomeService } from "./home.service";
import { OwlOptions } from "ngx-owl-carousel-o";
import * as AOS from "aos";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import { Subject } from "rxjs";

declare var $: any;

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit, AfterViewInit, OnDestroy {
  currentYear: number = new Date().getFullYear();
  public destroyed = new Subject<any>();

  customOptions: OwlOptions = {
    center: false,
    items: 1,
    loop: true,
    smartSpeed: 700,
    stagePadding: 15,
    margin: 20,
    autoplay: true,
    nav: true,
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

  constructor(private homeService: HomeService, private router: Router) {}

  ngOnInit(): void {
    AOS.init({});

    this.router.events
      .pipe(
        filter((event: RouterEvent) => event instanceof NavigationEnd),
        takeUntil(this.destroyed)
      )
      .subscribe(() => {
        siteMenuClone();
      });

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

    // tslint:disable-next-line:only-arrow-functions
    $("#inpt_search").keydown(function(e) {
      // prevent: "e", "=", ",", "-", "."
      if ([69, 187, 188, 189, 190, 107, 109, 110].includes(e.keyCode)) {
        e.preventDefault();
      }
    });

    // tslint:disable-next-line:only-arrow-functions
    $(document).on("keypress", function(e) {
      if (e.which === 13) {
        // tslint:disable-next-line:only-arrow-functions
        $("#search_form").submit(function(ex) {
          ex.preventDefault(); // stop form submit and do your ajax call

          const searchData = $("#inpt_search").val();
          $.ajax({
            type: "POST",
            url: "servlet_home",
            data: { search: searchData },
            cache: false,
            success(response) {
              if (response === "Connection Error!") {
                alert(
                  "Oops! Seems like we faced an internal server error! Please try performing the search again."
                );
              } else if (response === "Invalid Input!") {
                // tslint:disable-next-line:max-line-length
                alert(
                  "Oops! No smartphones available for the requested price!\n\n - You probably entered a rather low price for a smartphone\n\n\nHint: Try entering a value between 10,000 and 300,000"
                );
              } else if (response === "No Internet!") {
                alert(
                  "Oops! Seems like there's no stable connection available! Please check your internet connection and try again."
                );
              } else if (response === "No Smartphones!") {
                // tslint:disable-next-line:max-line-length
                alert(
                  "Oops! Seems like there are no smartphones available for the budget you provided. Please try performing the search again with a higher amount."
                );
              } else {
                location.href = "servlet_budgetResults";
              }
            }
          });
        });
      }
    });

    // tslint:disable-next-line:only-arrow-functions
    const siteMenuClone = function() {
      $('<div class="site-mobile-menu"></div>').prependTo(".site-wrap");

      $('<div class="site-mobile-menu-header"></div>').prependTo(
        ".site-mobile-menu"
      );
      $('<div class="site-mobile-menu-logo"></div>').prependTo(
        ".site-mobile-menu-header"
      );
      // tslint:disable-next-line:max-line-length
      $(
        '<div style="text-align: center;"><div class="site-mobile-menu-close "/><hr style="background-color: gainsboro"/></div>'
      ).prependTo(".site-mobile-menu-header");

      $('<div class="site-mobile-menu-body"></div>').appendTo(
        ".site-mobile-menu"
      );

      $(".js-logo-clone")
        .clone()
        .appendTo(".site-mobile-menu-logo");

      $('<span class="icon-close js-menu-toggle"></div>').prependTo(
        ".site-mobile-menu-close"
      );

      $(".js-clone-nav").each(function() {
        const $this = $(this);
        $this
          .clone()
          .attr("class", "site-nav-wrap")
          .appendTo(".site-mobile-menu-body");
      });

      // tslint:disable-next-line:only-arrow-functions
      setTimeout(function() {
        let counter = 0;
        $(".site-mobile-menu .has-children").each(function() {
          const $this = $(this);

          $this.prepend('<span class="arrow-collapse collapsed">');

          $this.find(".arrow-collapse").attr({
            "data-toggle": "collapse",
            "data-target": "#collapseItem" + counter
          });

          $this.find("> ul").attr({
            class: "collapse",
            id: "collapseItem" + counter
          });

          counter++;
        });
      }, 1000);

      $("body").on("click", ".arrow-collapse", function(e) {
        const $this = $(this);
        if (
          $this
            .closest("li")
            .find(".collapse")
            .hasClass("show")
        ) {
          $this.removeClass("active");
        } else {
          $this.addClass("active");
        }
        e.preventDefault();
      });

      $(window).resize(function() {
        // tslint:disable-next-line:one-variable-per-declaration
        const $this = $(this),
          w = $this.width();

        if (w > 768) {
          if ($("body").hasClass("offcanvas-menu")) {
            $("body").removeClass("offcanvas-menu");
          }
        }
      });

      $("body").on("click", ".js-menu-toggle", function(e) {
        const $this = $(this);
        e.preventDefault();

        if ($("body").hasClass("offcanvas-menu")) {
          $("body").removeClass("offcanvas-menu");
          $this.removeClass("active");
        } else {
          $("body").addClass("offcanvas-menu");
          $this.addClass("active");
        }
      });

      // click outisde offcanvas
      // tslint:disable-next-line:only-arrow-functions
      $(document).mouseup(function(e) {
        const container = $(".site-mobile-menu");
        if (!container.is(e.target) && container.has(e.target).length === 0) {
          if ($("body").hasClass("offcanvas-menu")) {
            $("body").removeClass("offcanvas-menu");
          }
        }
      });
    };
    siteMenuClone();
  }

  ngAfterViewInit(): void {
    // 'use strict';
  }

  ngOnDestroy(): void {
    this.destroyed.next();
    this.destroyed.complete();
  }

  showSmartphoneDealerDetails(dealerName: any) {
    this.homeService.setDealerName(dealerName);
    this.router.navigate(["/dealer"]);
  }
}
