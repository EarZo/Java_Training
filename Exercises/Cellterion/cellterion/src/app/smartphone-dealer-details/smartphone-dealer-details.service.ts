import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class SmartphoneDealerDetailsService extends DataService {
  constructor(http: HttpClient) {
    super(http, "//localhost:8082/services/dealer/name/");
  }
}
