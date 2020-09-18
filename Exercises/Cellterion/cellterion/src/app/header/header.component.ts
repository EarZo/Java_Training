import { AuthService } from "./../services/auth.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import * as AOS from "aos";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  dealerName: any;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    AOS.init({});
  }

  showSmartphoneDetails(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
    // this.router.navigate(["/details"]);
  }
}
