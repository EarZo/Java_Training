import { Component, OnInit, OnDestroy } from "@angular/core";
import { HomeService } from "./home.service";
import { OwlOptions } from "ngx-owl-carousel-o";
import * as AOS from "aos";
import { filter, takeUntil } from "rxjs/operators";
import { NavigationEnd, Router, RouterEvent } from "@angular/router";
import { Subject } from "rxjs";
import { Title } from "@angular/platform-browser";

declare var $: any;

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit, OnDestroy {
  budget;
  currentYear: number = new Date().getFullYear();
  public destroyed = new Subject<any>();

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

  constructor(
    private homeService: HomeService,
    private router: Router,
    private titleService: Title
  ) {
    this.titleService.setTitle("Cellterion");
  }

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

    $("#inpt_search").on("paste", function(e) {
      e.preventDefault();
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

  ngOnDestroy(): void {
    this.destroyed.next();
    this.destroyed.complete();
  }

  showBudgetResults(budget: number) {
    this.homeService.setBudget(budget);
    this.router.navigate(["/budget"]);
  }

  showSmartphoneDealerDetails(dealerName: any) {
    this.homeService.setDealerName(dealerName);
    this.router.navigate(["/dealer"]);
  }

  showSmartphonesByBrand(brandName: any) {
    this.homeService.setBrandName(brandName);
    this.router.navigate(["/brand"]);
  }
}
