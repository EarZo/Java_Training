import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class SmartphoneDetailsService extends DataService {
  constructor(http: HttpClient) {
    super(
      http,
      "//localhost:8080/services/smartphone" +
        "/" +
        localStorage.getItem("smartphoneId")
    );
  }

  setDealerName(dealerName: any) {
    localStorage.setItem("dealerName", dealerName);
  }
}
