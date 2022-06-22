import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import { baseServerUrl } from 'src/app/util/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private client: HttpClient) { }
  isUserLoggedIn():boolean{
    const token=this.getToken();
    return (token!=undefined && token!=null);

  }

  login(username:string, password:string):Observable<string>{
    const url=baseServerUrl+"/login";
    const requestData={username, password};
   const Observable:Observable<string>= this.client.post(url, requestData, {responseType:"text"});
   return Observable;

  }

  register(username:string, password:string):any{
    const url=baseServerUrl+"/register";
    const requestData={username, password};
    const observable:Observable<any>=this.client.post(url, requestData);
    return observable;

  }
  saveToken(username:string,token:string){
    localStorage.setItem('username',username); 
    localStorage.setItem('token',token);
  
   }
   getToken(){
    return localStorage.getItem('token');
  }
  getUsername(){
    return localStorage.getItem('username');
  }

  removeUserNameAndToken() {
    localStorage.removeItem('userName');
    localStorage.removeItem('token');
  }
  

}
