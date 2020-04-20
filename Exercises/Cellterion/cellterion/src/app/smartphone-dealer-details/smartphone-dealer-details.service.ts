import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class SmartphoneDealerDetailsService {
  constructor(private http: HttpClient) {}

  getSmartphoneDealerDetails(): Observable<any> {
    return this.http.get(
      "//localhost:8082/services/dealer/name/" +
        localStorage.getItem("dealerName")
    );
  }
}
