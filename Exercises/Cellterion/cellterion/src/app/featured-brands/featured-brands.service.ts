import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class FeaturedBrandsService {
  constructor() {}

  setBrandName(brandName: any) {
    localStorage.setItem("brandName", brandName);
  }
}
