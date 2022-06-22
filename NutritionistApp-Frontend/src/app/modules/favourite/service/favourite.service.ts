import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Food } from 'src/app/model/Food';
import { HttpClient } from '@angular/common/http';
import { baseServerUrl } from 'src/app/util/constants';
import { AuthenticationService } from '../../authentication/service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  
  constructor(private client:HttpClient, private authservice:AuthenticationService) { }

  getFoodList():Observable<Food[]>{
  const username=this.authservice.getUsername();
    const url=baseServerUrl+"/favourites/findby"+"/"+username;
    const observable:Observable<Food[]>=this.client.get<Food[]>(url);
    return observable;
  }

  removeItemFromFavourite(food:Food):Observable<Food>{
    const username=this.authservice.getUsername();
    const fdcId=food.fdcId;
    const url=baseServerUrl+"/favourites/delete/"+username+"/"+fdcId;
    const observable:Observable<Food>=this.client.delete<Food>(url);
    return observable;

  }

}
