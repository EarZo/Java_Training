import { SignupService } from './signup.service';
import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import {faEnvelope, faKey, faUser} from "@fortawesome/free-solid-svg-icons";
import * as AOS from "aos";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

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

  newUser = {
    firstname: String,
    lastname: String,
    username: String,
    password: String,
    admin: false
  }

  constructor(
    private signupService : SignupService,
    private router: Router,
    private toastr: ToastrService
    ) {}

  ngOnInit(): void {
    AOS.init({});
  }

  signUp() {
    this.newUser.firstname = this.form.controls["firstname"].value;
    this.newUser.lastname = this.form.controls["lastname"].value;
    this.newUser.username = this.form.controls["email"].value;
    this.newUser.password = this.form.controls["password"].value;

    this.signupService.create(this.newUser).subscribe(result => {
      console.log(result);
    });

    this.toastr.info("Registration successful!", "Welcome to the Cellterion™ family!");
    this.router.navigate(["/login"]);
  }
}
