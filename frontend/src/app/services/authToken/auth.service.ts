import { Injectable } from '@angular/core';
import { AuthenticationControllerService } from "../services/authentication-controller.service";
import { catchError, Observable, of, throwError } from "rxjs";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private authService: AuthenticationControllerService
  ) {}

  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  isLoggedIn(): Observable<boolean> {
    const token = this.token;
    if (!token) {
      // If there's no token, the user is not logged in
      return of(false);
    }
    // If there's a token, attempt to validate the session
    return this.authService.sessionValid().pipe(
      map(response => {
        // If the session is valid, return true
        return response.isValid ?? false;
      }),
      catchError(error => {
        console.error('Session validation error:', error);
        if (error.status === 403) {
          this.clearToken();
        }
        return of(false);
      })
    );
  }

  clearToken(): void {
    localStorage.removeItem('token');
  }
}
