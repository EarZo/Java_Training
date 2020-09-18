import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from "@angular/router";
import { AdminAuthGuard } from "./admin-auth-guard.service";
import { AuthGuard } from "./auth-guard.service";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class MasterGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  private route: ActivatedRouteSnapshot;
  private state: RouterStateSnapshot;

  //This method gets triggered when the route is hit
  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    this.route = route;
    this.state = state;

    if (!route.data) {
      Promise.resolve(true);
      return;
    }

    //this.route.data.guards is an array of strings set in routing configuration

    if (!this.route.data.guards || !this.route.data.guards.length) {
      Promise.resolve(true);
      return;
    }
    return this.executeGuards();
  }

  //Execute the guards sent in the route data
  private executeGuards(guardIndex: number = 0): Promise<boolean> {
    return this.activateGuard(this.route.data.guards[guardIndex])
      .then(() => {
        if (guardIndex < this.route.data.guards.length - 1) {
          return this.executeGuards(guardIndex + 1);
        } else {
          return Promise.resolve(true);
        }
      })
      .catch(() => {
        return Promise.reject(false);
      });
  }

  //Create an instance of the guard and fire canActivate method returning a promise
  private activateGuard(guardKey: string): Promise<boolean> {
    let guard: AuthGuard | AdminAuthGuard;

    switch (guardKey) {
      case GUARDS.AUTHGUARD:
        guard = new AuthGuard(this.router, this.authService);
        break;
      case GUARDS.ADMINAUTHGUARD:
        guard = new AdminAuthGuard(this.router, this.authService);
        break;
      default:
        break;
    }
    return guard.canActivate(this.route, this.state);
  }
}

export const GUARDS = {
  AUTHGUARD: "AUTHGUARD",
  ADMINAUTHGUARD: "ADMINAUTHGUARD"
};
