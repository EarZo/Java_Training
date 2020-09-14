import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { DataService } from "../services/data.service";

@Injectable({
  providedIn: "root"
})
export class SearchResultsService extends DataService {
  constructor(http: HttpClient) {
    super(
      http,
      "//localhost:8080/services/smartphone/all/price" +
        "/" +
        localStorage.getItem("budget")
    );
  }

  setId(id: number) {
    localStorage.setItem("smartphoneId", id.toString());
  }
}
