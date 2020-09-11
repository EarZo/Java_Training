import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";
import { NotFoundError } from "../common/not-found-error";
import { AppError } from "../common/app-error";

@Injectable({
  providedIn: "root"
})
export class SmartphoneDealerDetailsService {
  private url = "//localhost:8082/services/dealer/name";

  constructor(private http: HttpClient) {}

  getSmartphoneDealerDetails(): Observable<any> {
    return this.http
      .get(this.url + "/" + localStorage.getItem("dealerName"))
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error instanceof HttpErrorResponse && error.status == 404) {
            if (error.status === 404) {
              return Observable.throw(new NotFoundError());
            } else {
              return Observable.throw(new AppError(error));
            }
          }
        })
      );
  }
}
