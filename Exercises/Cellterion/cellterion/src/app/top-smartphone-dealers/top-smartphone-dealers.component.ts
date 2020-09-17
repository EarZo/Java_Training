import { Component, OnInit } from "@angular/core";
import { OwlOptions } from "ngx-owl-carousel-o";

@Component({
  selector: "app-top-smartphone-dealers",
  templateUrl: "./top-smartphone-dealers.component.html",
  styleUrls: ["./top-smartphone-dealers.component.css"]
})
export class TopSmartphoneDealersComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

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
}
