import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class SearchService {
  constructor() {}

  setBudget(budget: number) {
    localStorage.setItem("budget", budget.toString());
  }
}
