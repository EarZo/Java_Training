import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DataService } from '../services/data.service';

@Injectable({
  providedIn: 'root'
})
export class SignupService extends DataService {
  constructor(http: HttpClient) {
    super(http, "//localhost:8081/services/sign-up");
  }
}
