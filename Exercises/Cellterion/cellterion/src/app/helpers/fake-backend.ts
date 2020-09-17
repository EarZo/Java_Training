import { Injectable } from "@angular/core";
import {
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from "@angular/common/http";
import { Observable, of, throwError } from "rxjs";
import { delay, mergeMap, materialize, dematerialize } from "rxjs/operators";

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const { url, method, headers, body } = request;

    // fake jwt token
    let token =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkVyYW5kYSBFZGlyaXNvb3JpeWEiLCJhZG1pbiI6dHJ1ZX0.oHOYLqrurmbwJP0ogYq1YAYsW5MLhUdOLHekIPCS_s8";

    // wrap in delayed observable to simulate server api call
    return of(null)
      .pipe(mergeMap(handleRoute))
      .pipe(materialize()) // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
      .pipe(delay(1000))
      .pipe(dematerialize());

    function handleRoute() {
      /* console.log(url);
      console.log(method);
      console.log(headers);
      console.log(body); */
      switch (true) {
        case url.endsWith("/api/authenticate") && method === "POST":
          return authenticate();
        case url.endsWith("/api/orders") && method === "GET":
          return getOrders();
        default:
          // pass through any requests not handled above
          return next.handle(request);
      }
    }

    // route functions

    //
    // fake implementation of /api/authenticate
    //
    function authenticate() {
      const requestBody = body;

      if (
        requestBody.email === "eranda@domain.com" &&
        requestBody.password === "1234"
      ) {
        return ok({ token: token });
      } else {
        return ok();
      }
    }

    //
    // fake implementation of /api/orders
    //

    function getOrders() {
      const requestHeaders = headers;
      if (isLoggedIn()) {
        return ok([1, 2, 3]);
      } else {
        return unauthorized();
      }
    }

    // helper functions

    function ok(body?) {
      return of(new HttpResponse({ status: 200, body }));
    }

    function unauthorized() {
      return throwError({ status: 401, error: { message: "Unauthorised" } });
    }

    function error(message) {
      return throwError({ error: { message } });
    }

    function isLoggedIn() {
      return headers.get("Authorization") === "Bearer " + token;
    }
  }
}

export let fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};
