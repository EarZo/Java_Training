import { Injectable } from "@angular/core";
import { Observable, empty, of } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class SmartphonesByBrandService {
  constructor(private http: HttpClient) {}

  getDetails(): Observable<any> {
    return this.http
      .get(
        "//localhost:8083/services/brand/name/" +
          localStorage.getItem("brandName")
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error instanceof HttpErrorResponse && error.status == 404) {
            return of({});
          }
        })
      );
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
