import { Component, OnInit } from '@angular/core';
import {SmartphoneDetailsService} from './smartphone-details.service';
import {filter, takeUntil} from 'rxjs/operators';
import {NavigationEnd, Router, RouterEvent} from '@angular/router';
import * as AOS from 'aos';
import {LatestSmartphonesService} from '../latest-smartphones/latest-smartphones.service';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-smartphone-details',
  templateUrl: './smartphone-details.component.html',
  styleUrls: ['./smartphone-details.component.css']
})
export class SmartphoneDetailsComponent implements OnInit {
  private smartphoneId: number;
  public destroyed = new Subject<any>();
  smartphoneDetails: Array<any>;

  // tslint:disable-next-line:max-line-length
  constructor(private smartphoneDetailsService: SmartphoneDetailsService, private latestSmartphonesService: LatestSmartphonesService, private router: Router) { }

  ngOnInit(): void {
    AOS.init({
    });

    this.latestSmartphonesService.currentSmartphoneId.subscribe(data => {
      this.smartphoneId = data;
    });

    this.fetchData();

    this.router.events.pipe(
      filter((event: RouterEvent) => event instanceof NavigationEnd),
      takeUntil(this.destroyed)
    ).subscribe(() => {
      this.fetchData();
    });
  }


  fetchData() {
    this.smartphoneDetailsService.getDetails(this.smartphoneId).subscribe(data => {
      this.smartphoneDetails = data;
    });
  }

}
