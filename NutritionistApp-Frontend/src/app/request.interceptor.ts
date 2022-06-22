import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthenticationService } from "./modules/authentication/service/authentication.service";
import { baseServerUrl, mainApi } from "./util/constants";



@Injectable({
    providedIn:'root'
})
export class AuthenticationInterceptor  implements HttpInterceptor{

    constructor(private authService:AuthenticationService){
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const loggedIn: boolean = this.authService.isUserLoggedIn();
        if (request.url == mainApi) {
            return next.handle(request);
        }
        if (!loggedIn) {
            return next.handle(request);
        }
        const token: string | null = this.authService.getToken();
        if (token != null) {
            const headers: HttpHeaders = new HttpHeaders({ token });
            request = request.clone({ headers });
        }
        return next.handle(request);
    }
    
}