import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Food } from 'src/app/model/Food';
import { baseServerUrl, mainApi } from 'src/app/util/constants';
import { AuthenticationService } from '../../authentication/service/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class FooditemsService {

  constructor(private client:HttpClient, private authService:AuthenticationService) { }
  
  getFoodList():Observable<Food[]>{
    const url=mainApi;
    const observable:Observable<Food[]>=this.client.get<Food[]>(url);
    return observable;

  }

  addToFavourite(foodData:Food):Observable<Food>{
    
    const username=this.authService.getUsername();
    console.log("username in add ",username);
    const requestdata={...foodData,username};

    const url=baseServerUrl+"/favourites/add";
    const observable:Observable<Food>=this.client.post<Food>(url, requestdata);
    return observable;
  }





}
