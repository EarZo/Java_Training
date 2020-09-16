import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class OrderService {
  constructor(private http: HttpClient) {}

  getOrders() {
    /* let headers = new Headers();
    let token = localStorage.getItem("token");
    headers.append("Authorization", "Bearer " + token);

    let options = new RequestOptions({ headers: headers }); */

    return this.http.get("/api/orders");
  }
}
