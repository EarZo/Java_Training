import { BrowserModule, Title } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

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
    SearchResultsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    CarouselModule,
    SlickCarouselModule,
    BrowserAnimationsModule
  ],
  providers: [Title],
  bootstrap: [AppComponent]
})
export class AppModule {}
