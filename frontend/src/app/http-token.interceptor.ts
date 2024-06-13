import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { inject } from '@angular/core';

export const httpTokenInterceptor: HttpInterceptorFn = (request, next) => {
  const router = inject(Router)
  console.log("httpTokenIntterceptor")
  if (request.url.includes('/login')) {
    return next(request);
  }
  let token = localStorage.getItem("token");
  if (token) {
    request = request.clone({
      setHeaders:{
        "Authorization": "Bearer " + token,
        "Content-Type": "application/json"
      }
    })
  }
  return next(request).pipe(
    catchError(() => { 
      console.log("removing token")
      localStorage.removeItem("token")
      if(router.url == "/product")
        location.reload()
      else
        router.navigate(['product'])
      return next(request);
    })
  );
};
