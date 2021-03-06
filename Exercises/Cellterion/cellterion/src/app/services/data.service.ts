import { UnauthorizedError } from './../common/unauthorized-error';
import { AppError } from "./../common/app-error";
import { BadInputError } from "../common/bad-input-error";
import { NotFoundError } from "./../common/not-found-error";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

@Injectable()
export class DataService {
  constructor(private http: HttpClient, private url: string) {}

  getAll(additionalParams?: string): Observable<any> {
    if (additionalParams) {
      return this.http
        .get(this.url + additionalParams)
        .pipe(catchError(this.handleError));
    } else {
      return this.http.get(this.url).pipe(catchError(this.handleError));
    }
  }

  create(resource) {
    return this.http
      .post(this.url, resource)
      .pipe(catchError(this.handleError));
  }

  updateWithPatch(resource) {
    return this.http
      .patch(this.url + "/" + resource.id, resource)
      .pipe(catchError(this.handleError));
  }

  updateWithPut(resource) {
    return this.http
      .put(this.url + "/" + resource.id, resource)
      .pipe(catchError(this.handleError));
  }

  delete(id) {
    return this.http
      .delete(this.url + "/" + id)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: Response) {
    if (error.status === 400) {
      return throwError(new BadInputError(error));
    } else if(error.status === 403){
      return throwError(new UnauthorizedError(error));
    }else if (error.status === 404) {
      return throwError(new NotFoundError(error));
    } else {
      return throwError(new AppError(error));
    }
  }
}
