import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject } from "rxjs";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class LatestSmartphonesService extends DataService {
  private smartphoneId = new BehaviorSubject(0);
  currentSmartphoneId = this.smartphoneId.asObservable();
  currentYear: number = new Date().getFullYear();

  constructor(http: HttpClient) {
    super(
      http,
      "//localhost:8080/services/smartphone/all" +
        "/" +
        new Date().getFullYear()
    );
  }

  setId(id: number) {
    this.smartphoneId.next(id);
    localStorage.setItem("smartphoneId", id.toString());
  }
}
