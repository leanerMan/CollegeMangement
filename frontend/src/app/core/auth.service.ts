import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { StorageService } from './storage.service';
// import { AppCookieService } from './app-cookie.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const KEY='accessToken'

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private storageService=inject(StorageService)

  getJWTToken() {
    return this.storageService.getToken();
  }

  constructor(private http:HttpClient) { }

  login(username:string,password:string,type:string):Observable<any>{
    return this.http.post(`${environment.apiUrlMe}auth/signin`,{username,password,type},httpOptions);
  }

  logout(){
    this.storageService.clean();
  }


  // refreshToken() {
  //   return this.http.post(`${environment.apiUrlMe}auth/refreshtoken`, { }, httpOptions);
  // }


}
