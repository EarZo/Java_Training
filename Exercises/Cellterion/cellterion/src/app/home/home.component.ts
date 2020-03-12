import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  SlideOptions = { items: 1, dots: true, nav: true };
  CarouselOptions = { items: 3, dots: true, nav: true };
}
