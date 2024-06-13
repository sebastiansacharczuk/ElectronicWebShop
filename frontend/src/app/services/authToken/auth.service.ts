import { Injectable } from '@angular/core';
import { AuthenticationControllerService } from "../services/authentication-controller.service";
import { catchError, Observable, of, throwError } from "rxjs";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 
  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  isLoggedIn() {
    if(localStorage.getItem("token"))
      return true
    else
      return false
  }

  clearToken(): void {
    localStorage.removeItem('token');
  }
}
