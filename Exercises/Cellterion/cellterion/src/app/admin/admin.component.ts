import { Component, OnInit } from "@angular/core";
import { OrderService } from "../services/order.service";
import * as AOS from "aos";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {
  orders: any;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    AOS.init({});
    this.orderService.getOrders().subscribe(orders => (this.orders = orders));
  }
}
