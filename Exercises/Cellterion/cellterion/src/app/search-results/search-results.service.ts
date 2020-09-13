import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";
import { AppError } from "../common/app-error";
import { NotFoundError } from "../common/not-found-error";

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
          return Observable.throw(new NotFoundError());
        } else {
          return Observable.throw(new AppError(error));
        }
      })
    );
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
