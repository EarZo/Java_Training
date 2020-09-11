import { NotFoundError } from "./../common/not-found-error";
import { AppError } from "./../common/app-error";
import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class LatestSmartphonesService {
  private url = "//localhost:8080/services/smartphone/all";
  private smartphoneId = new BehaviorSubject(0);
  currentSmartphoneId = this.smartphoneId.asObservable();
  currentYear: number = new Date().getFullYear();

  constructor(private http: HttpClient) {}

  getLatest(): Observable<any> {
    return this.http.get(this.url + "/" + this.currentYear).pipe(
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
    this.smartphoneId.next(id);
    localStorage.setItem("smartphoneId", id.toString());
  }
}
