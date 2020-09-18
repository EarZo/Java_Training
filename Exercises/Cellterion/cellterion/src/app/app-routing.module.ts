import { SignupComponent } from "./signup/signup.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { LatestSmartphonesComponent } from "./latest-smartphones/latest-smartphones.component";
import { SmartphoneDetailsComponent } from "./smartphone-details/smartphone-details.component";
import { SmartphoneDealerDetailsComponent } from "./smartphone-dealer-details/smartphone-dealer-details.component";
import { SmartphonesByBrandComponent } from "./smartphones-by-brand/smartphones-by-brand.component";
import { SearchResultsComponent } from "./search-results/search-results.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { AdminComponent } from "./admin/admin.component";
import { LoginComponent } from "./login/login.component";
import { NoAccessComponent } from "./no-access/no-access.component";
import { AdminAuthGuard } from "./services/admin-auth-guard.service";
import { AuthGuard } from "./services/auth-guard.service";
import { GUARDS, MasterGuard } from "./services/master-guard.service";

const routes: Routes = [
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full"
  },
  {
    path: "admin",
    component: AdminComponent,
    canActivate: [AuthGuard, AdminAuthGuard, MasterGuard],
    //this is the data object which will be used by
    //masteer guard to execute guard1 and guard 2
    data: {
      guards: [GUARDS.AUTHGUARD, GUARDS.ADMINAUTHGUARD]
    }
  },
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "no-access", component: NoAccessComponent },
  {
    path: "latest-smartphones",
    component: LatestSmartphonesComponent
  },
  {
    path: "details",
    component: SmartphoneDetailsComponent
  },
  {
    path: "dealer/:dealerName",
    component: SmartphoneDealerDetailsComponent
  },
  {
    path: "brand/:brandName",
    component: SmartphonesByBrandComponent
  },
  {
    path: "budget",
    component: SearchResultsComponent
  },
  {
    path: "**",
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: "enabled",
      onSameUrlNavigation: "reload"
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
