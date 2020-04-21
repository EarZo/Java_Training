import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class HomeService {
  constructor() {}

  setDealerName(dealerName: any) {
    localStorage.setItem("dealerName", dealerName);
  }

  setBrandName(brandName: any) {
    localStorage.setItem("brandName", brandName);
  }
}
