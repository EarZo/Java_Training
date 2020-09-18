import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class AdminAuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(): Promise<boolean> {
    let user = this.authService.currentUser;

    return new Promise((resolve: Function, reject: Function) => {
      if (user && user.admin) {
        resolve(true);
      } else {
        this.router.navigate(["/no-access"]);
        reject(false);
      }
    });
  }
}
