import { HttpInterceptorFn } from '@angular/common/http';

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  let token = localStorage.getItem("token");
  if (token) {
    req = req.clone({
      setHeaders:{
        "Authorization": "Bearer " + token,
        "Content-Type": "application/json"
      }
    })
  }
  return next(req);
};
