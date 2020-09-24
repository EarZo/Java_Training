import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import {
  faCoffee,
  faEnvelope,
  faKey,
  faUser
} from "@fortawesome/free-solid-svg-icons";
import * as AOS from "aos";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {
  faEnvelope = faEnvelope;
  faKey = faKey;
  faUser = faUser;

  form = new FormGroup({
    firstname: new FormControl("", [Validators.required]),
    lastname: new FormControl("", [Validators.required]),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.required]),
    reEnterPassword: new FormControl("", [Validators.required])
  });

  constructor() {}

  ngOnInit(): void {
    AOS.init({});
  }

  signUp() {
    console.log(this.form.value);
  }
}
