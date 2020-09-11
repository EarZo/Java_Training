import { Injectable } from "@angular/core";
import { Observable, empty } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class SearchResultsService {
  private url = "//localhost:8080/services/smartphone/all/price";

  constructor(private http: HttpClient) {}

  getDetails(): Observable<any> {
    return this.http.get(this.url + "/" + localStorage.getItem("budget")).pipe(
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
