import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import { map } from "rxjs/operators";
import { ToastrService } from "ngx-toastr";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) {}

  login(credentials) {
    return this.http.post<any>("http://localhost:8081/login", credentials, {observe: 'response'}).pipe(
      map(response => {
        console.log(response.headers.get('Authorization'));
        console.log(response.headers.get('XSRF-TOKEN'));
        if (response && response.headers.get('Authorization')) {
          localStorage.setItem("token", response.headers.get('Authorization'));
          console.log(new JwtHelperService().decodeToken(localStorage.getItem("token")));
          return true;
        }
        return false;
      })
    );
  }

  logout() {
    this.toastr.info("You've successfully signed out!");
    localStorage.removeItem("token");
    this.router.navigate(["/home"]);
  }

  isLoggedIn() {
    let jwtHelperService = new JwtHelperService();
    let token = localStorage.getItem("token");

    if (!token) {
      return false;
    }

    // let decodedToken = jwtHelperService.decodeToken(token);
    let expirationDate = jwtHelperService.getTokenExpirationDate(token);
    let isExpired = jwtHelperService.isTokenExpired(token);

    return !isExpired;
  }

  get currentUser() {
    let token = localStorage.getItem("token");
    if (!token) {
      return null;
    }
    return new JwtHelperService().decodeToken(localStorage.getItem("token"));
  }
}
