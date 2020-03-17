import { Component, OnInit } from '@angular/core';
import { LatestSmartphonesService } from './latest-smartphones.service';

@Component({
  selector: 'app-latest-smartphones',
  templateUrl: './latest-smartphones.component.html',
  styleUrls: ['./latest-smartphones.component.css']
})
export class LatestSmartphonesComponent implements OnInit {
  currentYear: number = new Date().getFullYear();
  latestSmartphones: Array<any>;

  constructor(private latestSmartphonesService: LatestSmartphonesService) { }

  ngOnInit(): void {
    this.latestSmartphonesService.getAll().subscribe(data => {
      this.latestSmartphones = data;
    });
  }

}
