import {  HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

import { AuthService } from '../core/auth.service';



// let isRefreshing = false;
// let httpClient: HttpClient; 

// export function httpInterceptor(req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> {
//   req = req.clone({
//     withCredentials: true,
//   });
//   const auth=inject(AuthService);
//   return next(req).pipe(
//     catchError((error) => {
//       if (error instanceof HttpErrorResponse && !req.url.includes('auth/signin') && error.status === 401) {
       
//         return handle401Error(req, next,auth);
//       }
//       return throwError(() => error);
//     })
//   );
// }

// function handle401Error(req: HttpRequest<any>, next: HttpHandlerFn,auth :AuthService): Observable<HttpEvent<any>> {
//   if (!isRefreshing) {
//     isRefreshing = true;
//     const isLoggedIn = new StorageService().isLoggedIn();
//     // const authService = new AuthService(httpClient);
//     const eventBusService = new EventBusService();

//     if (isLoggedIn) {
//       return auth.refreshToken().pipe(
//         switchMap(() => {
//           isRefreshing = false;
//           return next(req);
//         }),
//         catchError((error) => {
//           isRefreshing = false;
//           if (error.status == 403) {
//             eventBusService.emit(new EventData('logout', null));
//           }
//           return throwError(() => error);
//         })
//       );
//     }
//   }
//   return next(req);
// }

// type HttpHandlerFn = (req: HttpRequest<any>) => Observable<HttpEvent<any>>;

export const httpInterceptor: HttpInterceptorFn = (req, next) => {
  //   let authReq=req;
  //   const token =inject(StorageService).getToken();
  //   if(token){
  //     authReq=req.clone({headers:req.headers.set('Authorization','Bearer '+token)})
  //     console.log(token);
  //     return next(authReq);
  //  }
  // req = req.clone({
  //   withCredentials: true,
  // });
  //   return next(req);
  
  
  // console.log("================== interceptor ==============================")
  const token = inject(AuthService).getJWTToken();
  
  if(token){
    req = req.clone({
      url:  req.url,
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  
  
  // console.log(`Bearer ${token}`);
  return next(req);
  };