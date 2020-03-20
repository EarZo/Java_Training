import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LatestSmartphonesComponent } from './latest-smartphones/latest-smartphones.component';
import { SmartphoneDetailsComponent } from './smartphone-details/smartphone-details.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, HeaderComponent, FooterComponent, LatestSmartphonesComponent, SmartphoneDetailsComponent],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule, CarouselModule, SlickCarouselModule, BrowserAnimationsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
