import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError } from "rxjs/operators";
import { NotFoundError } from "../common/not-found-error";
import { AppError } from "../common/app-error";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class SmartphoneDealerDetailsService extends DataService {
  constructor(http: HttpClient) {
    super(
      http,
      "//localhost:8082/services/dealer/name" +
        "/" +
        localStorage.getItem("dealerName")
    );
  }
}
