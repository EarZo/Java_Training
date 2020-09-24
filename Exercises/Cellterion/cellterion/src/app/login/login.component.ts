import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AuthService } from "../services/auth.service";
import * as AOS from "aos";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { faEnvelope, faKey } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  faEnvelope = faEnvelope;
  faKey = faKey;
  invalidLogin: boolean;

  form = new FormGroup({
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

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

  signIn() {
    this.authService.login(this.form.value).subscribe(result => {
      if (result) {
        let returnUrl = this.route.snapshot.queryParamMap.get("returnUrl");
        this.router.navigate([returnUrl || "/home"]);
      } else this.invalidLogin = true;
    });
  }
}
