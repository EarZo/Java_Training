import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { HomeDirective } from './home/home.directive';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LatestSmartphonesComponent } from './latest-smartphones/latest-smartphones.component';
import { LatestSmartphonesDirective } from './latest-smartphones/latest-smartphones.directive';

@NgModule({
  declarations: [
    AppComponent, HomeComponent, HomeDirective, HeaderComponent, FooterComponent, LatestSmartphonesComponent, LatestSmartphonesDirective
  ],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule, CarouselModule, SlickCarouselModule, BrowserAnimationsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
