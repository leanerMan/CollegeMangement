import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';
const TOKEN='token'

@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor() { }

  clean():void{
    window.sessionStorage.clear();
    window.location.reload();
  }

  public saveUser(res: any): void {
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(res.user));
    window.sessionStorage.setItem(TOKEN, JSON.stringify(res.token));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return null;
  }

  public getToken(): any {
    const token = window.sessionStorage.getItem(TOKEN);
    if (token !== null && token !== "undefined")  {
      return JSON.parse(token);
    }

    return null;
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }

  public getRoles(){
    let user1=this.getUser();
    if(user1){
      return user1.roles;
    }
    
    return ''
  }


}
