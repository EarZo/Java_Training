import { Injectable } from "@angular/core";
import {
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpHeaders
} from "@angular/common/http";
import { Observable, of, throwError } from "rxjs";
import { delay, mergeMap, materialize, dematerialize } from "rxjs/operators";
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor(private injectedCookieService: CookieService){}

  intercept(
    request: HttpRequest<any>, 
    next: HttpHandler
    ): Observable<HttpEvent<any>> {
      
      const cookieService = this.injectedCookieService;
      let newHeaders: HttpHeaders = request.headers;
      const { url, method, headers, body } = request;
      
      let token = localStorage.getItem("token");
      
      return of(null)
      .pipe(mergeMap(handleRoute))
      .pipe(materialize())
      .pipe(delay(1000))
      .pipe(dematerialize());

      function handleRoute() {
        switch (true) {
          case url.endsWith("/api/orders") && method === "GET":
            return getOrders();
          default:
            return authenticate();
        }
      }
    
      function authenticate() {
        let csrfToken = cookieService.get("XSRF-TOKEN");
      
        if (csrfToken === null || csrfToken === undefined) {
          newHeaders = newHeaders.append('X-XSRF-TOKEN', 'undefined');
        } else {
          newHeaders = newHeaders.append('X-XSRF-TOKEN', csrfToken);
        }
      
        const authReq = request.clone({headers: newHeaders, withCredentials: true});
        return next.handle(authReq);
      }
    
      function getOrders() {
        if (isLoggedIn()) {
          return ok([1, 2, 3]);
        } else {
          return unauthorized();
        }
      }
    
      function ok(body?) {
        return of(new HttpResponse({ status: 200, body }));
      }
    
      function unauthorized() {
        return throwError({ status: 401, error: { message: "Unauthorised" } });
      }
    
      function isLoggedIn() {
        return headers.get("Authorization") === "Bearer " + token;
      }
    }
}

export let fakeBackendProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true,
  deps: [CookieService]
};
