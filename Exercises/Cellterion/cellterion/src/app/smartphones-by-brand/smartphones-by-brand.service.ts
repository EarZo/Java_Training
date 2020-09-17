import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class SmartphonesByBrandService extends DataService {
  constructor(http: HttpClient) {
    super(http, "//localhost:8083/services/brand/name/");
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
