import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { HomeDirective } from './home/home.directive';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, HomeDirective, HeaderComponent, FooterComponent],
  imports: [BrowserModule, AppRoutingModule, CarouselModule, SlickCarouselModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
