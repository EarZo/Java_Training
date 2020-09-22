import { AppErrorHandler } from "./common/app-error-handler";
import { BrowserModule, Title } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ErrorHandler, NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { JwtModule } from "@auth0/angular-jwt";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { CarouselModule } from "ngx-owl-carousel-o";
import { SlickCarouselModule } from "ngx-slick-carousel";
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { LatestSmartphonesComponent } from "./latest-smartphones/latest-smartphones.component";
import { SmartphoneDetailsComponent } from "./smartphone-details/smartphone-details.component";
import { SmartphoneDealerDetailsComponent } from "./smartphone-dealer-details/smartphone-dealer-details.component";
import { SmartphonesByBrandComponent } from "./smartphones-by-brand/smartphones-by-brand.component";
import { SearchResultsComponent } from "./search-results/search-results.component";
import { SearchComponent } from "./search/search.component";
import { FeaturedBrandsComponent } from "./featured-brands/featured-brands.component";
import { TopSmartphoneDealersComponent } from "./top-smartphone-dealers/top-smartphone-dealers.component";
import { ToastrModule } from "ngx-toastr";
import { AdminComponent } from "./admin/admin.component";
import { LoginComponent } from "./login/login.component";
import { NoAccessComponent } from "./no-access/no-access.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { SignupComponent } from "./signup/signup.component";
import {
  FakeBackendInterceptor,
  fakeBackendProvider
} from "./helpers/fake-backend";
import { OrderService } from "./services/order.service";
import { AuthService } from "./services/auth.service";
import { AuthGuard } from "./services/auth-guard.service";
import { AdminAuthGuard } from "./services/admin-auth-guard.service";
import { MasterGuard } from "./services/master-guard.service";

export function tokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LatestSmartphonesComponent,
    SmartphoneDetailsComponent,
    SmartphoneDealerDetailsComponent,
    SmartphonesByBrandComponent,
    SearchResultsComponent,
    SearchComponent,
    FeaturedBrandsComponent,
    TopSmartphoneDealersComponent,
    AdminComponent,
    LoginComponent,
    NoAccessComponent,
    NotFoundComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter
        // allowedDomains: ["http://localhost:4200", "http://localhost:8080"]
        // throwNoTokenError: true,
        // skipWhenExpired: true

        /* disallowedRoutes: ["http://example.com/examplebadroute/"] */
      }
    }),
    AppRoutingModule,
    CarouselModule,
    SlickCarouselModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [
    { provide: ErrorHandler, useClass: AppErrorHandler },
    Title,
    OrderService,
    AuthService,
    MasterGuard,
    AuthGuard,
    AdminAuthGuard,

    // For creating a mock back-end. You don't need these in a real app.
    fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
