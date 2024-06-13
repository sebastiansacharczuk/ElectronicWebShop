import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';

export const httpTokenInterceptor: HttpInterceptorFn = (request, next) => {
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
      location.reload()
      return next(request);
    })
  );
};
