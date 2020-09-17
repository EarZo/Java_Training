import { AuthService } from "./../services/auth.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  dealerName: any;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  showSmartphoneDetails(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
    // this.router.navigate(["/details"]);
  }
}
