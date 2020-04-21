import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { LatestSmartphonesComponent } from "./latest-smartphones/latest-smartphones.component";
import { SmartphoneDetailsComponent } from "./smartphone-details/smartphone-details.component";
import { SmartphoneDealerDetailsComponent } from "./smartphone-dealer-details/smartphone-dealer-details.component";
import { SmartphonesByBrandComponent } from "./smartphones-by-brand/smartphones-by-brand.component";

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
    path: "latest-smartphones",
    component: LatestSmartphonesComponent
  },
  {
    path: "details",
    component: SmartphoneDetailsComponent
  },
  {
    path: "dealer",
    component: SmartphoneDealerDetailsComponent
  },
  {
    path: "brand",
    component: SmartphonesByBrandComponent
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
