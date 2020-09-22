import { Component, OnInit } from "@angular/core";
import { throwError } from "rxjs";
import { NotFoundError } from "../common/not-found-error";
import * as AOS from "aos";
import { faSmileBeam } from "@fortawesome/free-regular-svg-icons";

@Component({
  selector: "app-not-found",
  templateUrl: "./not-found.component.html",
  styleUrls: ["./not-found.component.css"]
})
export class NotFoundComponent implements OnInit {
  faSmileBeam = faSmileBeam;

  constructor() {}

  ngOnInit(): void {
    AOS.init({});
    throwError(new NotFoundError());
  }
}
