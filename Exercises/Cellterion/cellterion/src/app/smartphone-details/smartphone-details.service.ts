import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SmartphoneDetailsService {

  constructor(private http: HttpClient) { }

  getDetails(id): Observable<any> {
    return this.http.get('//localhost:8080/services/details/' + id);
  }
}
