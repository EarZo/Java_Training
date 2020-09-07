import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class TopSmartphoneDealersService {
  constructor() {}

  setDealerName(dealerName: any) {
    localStorage.setItem("dealerName", dealerName);
  }
}
