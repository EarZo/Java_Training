import { Injectable } from "@angular/core";
import { CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route, state: RouterStateSnapshot): Promise<boolean> {
    return new Promise((resolve: Function, reject: Function) => {
      if (this.authService.isLoggedIn()) {
        resolve(true);
      } else {
        this.router.navigate(["/login"], {
          queryParams: { returnUrl: state.url }
        });
        reject(false);
      }
    });
  }
}
