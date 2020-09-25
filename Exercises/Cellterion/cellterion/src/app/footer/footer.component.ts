import { Component, OnInit } from "@angular/core";
import {
  faAddressBook,
  faAddressCard,
  faLocationArrow,
  faPhone
} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-footer",
  templateUrl: "./footer.component.html",
  styleUrls: ["./footer.component.css"]
})
export class FooterComponent implements OnInit {
  faLocationArrow = faLocationArrow;
  faPhone = faPhone;
  faAddressCard = faAddressCard;

  currentYear: number = new Date().getFullYear();

  constructor() {}

  ngOnInit(): void {}
}
