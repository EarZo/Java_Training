import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { Title } from '@angular/platform-browser';

@Injectable({
  providedIn: "root"
})
export class LatestSmartphonesService {
  currentYear: number = new Date().getFullYear();
  private smartphoneId = new BehaviorSubject(0);
  currentSmartphoneId = this.smartphoneId.asObservable();

  constructor(private http: HttpClient, private titleService: Title) {
    this.titleService.setTitle( this.currentYear + " Latest Smartphones" );
  }

  getLatest(): Observable<any> {
    return this.http.get(
      "//localhost:8080/services/smartphone/all/" + this.currentYear
    );
  }

  setId(id: number) {
    this.smartphoneId.next(id);
    localStorage.setItem("smartphoneId", id.toString());
  }
}
