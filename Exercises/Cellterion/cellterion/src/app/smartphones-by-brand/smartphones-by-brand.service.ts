import { Injectable } from "@angular/core";
import { Observable, empty } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class SmartphonesByBrandService {
  private url = "//localhost:8083/services/brand/name";

  constructor(private http: HttpClient) {}

  getDetails(): Observable<any> {
    return this.http
      .get(this.url + "/" + localStorage.getItem("brandName"))
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 404) {
            return empty();
          }
        })
      );
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
