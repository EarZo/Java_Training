import { Component, OnInit, OnDestroy } from "@angular/core";
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
  currentYear: number = new Date().getFullYear();
  public destroyed = new Subject<any>();

  constructor(private router: Router, private titleService: Title) {
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

    const siteMenuClone = function() {
      $('<div class="site-mobile-menu"></div>').prependTo(".site-wrap");

      $('<div class="site-mobile-menu-header"></div>').prependTo(
        ".site-mobile-menu"
      );
      $('<div class="site-mobile-menu-logo"></div>').prependTo(
        ".site-mobile-menu-header"
      );
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
}
