import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AuthService } from "../services/auth.service";
import * as AOS from "aos";
import { FormGroup, FormControl, Validators } from "@angular/forms";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  invalidLogin: boolean;

  form = new FormGroup({
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

  user = {
    username: String,
    password: String
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    AOS.init({});
  }

  get email() {
    return this.form.get("email");
  }

  get password() {
    return this.form.get("password");
  }

  clearEmail(){
    this.form.controls.email.setValue('');
  }

  clearPassword(){
    this.form.controls.password.setValue('');
  }

  signIn() {
    this.user.username = this.form.controls["email"].value;
    this.user.password = this.form.controls["password"].value;

    this.authService.login(this.user).subscribe(result => {
      if (result) {
        let returnUrl = this.route.snapshot.queryParamMap.get("returnUrl");
        this.router.navigate([returnUrl || "/home"]);
      } else this.invalidLogin = true;
    });
  }
}
