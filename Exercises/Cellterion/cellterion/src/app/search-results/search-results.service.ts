import { Injectable } from "@angular/core";
import { Observable, empty } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class SearchResultsService {
  constructor(private http: HttpClient) {}

  getDetails(): Observable<any> {
    return this.http
      .get(
        "//localhost:8080/services/smartphone/all/price/" +
          localStorage.getItem("budget")
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error instanceof HttpErrorResponse && error.status == 404) {
            return empty();
          }
        })
      );
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
