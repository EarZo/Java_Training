import { AuthService } from "./../services/auth.service";
import { Component, OnInit } from "@angular/core";
import * as AOS from "aos";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  dealerName: any;

  constructor(public authService: AuthService) {}

  ngOnInit(): void {
    AOS.init({});
  }
}
