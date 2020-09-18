import { Component, OnInit } from "@angular/core";
import { throwError } from "rxjs";
import { NotFoundError } from "../common/not-found-error";
import * as AOS from "aos";

@Component({
  selector: "app-not-found",
  templateUrl: "./not-found.component.html",
  styleUrls: ["./not-found.component.css"]
})
export class NotFoundComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    AOS.init({});
    throwError(new NotFoundError());
  }
}
