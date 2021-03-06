import { Component, OnInit } from "@angular/core";
import { OwlOptions } from "ngx-owl-carousel-o";

@Component({
  selector: "app-featured-brands",
  templateUrl: "./featured-brands.component.html",
  styleUrls: ["./featured-brands.component.css"]
})
export class FeaturedBrandsComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  customOptions: OwlOptions = {
    autoplayHoverPause: true,
    autoplayMouseleaveTimeout: 500,
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
}
