import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from 'src/app/model/Food';
import { FooditemsService } from '../../service/fooditems.service';

@Component({
  selector: 'app-list-fooditems',
  templateUrl: './list-fooditems.component.html',
  styleUrls: ['./list-fooditems.component.css']
})
export class ListFooditemsComponent implements OnInit{

  foodArray: Food[] | undefined;
  errorMsg: String | undefined;
  addedFood:Food|undefined;

  constructor(private service: FooditemsService) {

  }
  ngOnInit(): void {
    this.fetchAllFoodItems();
    console.log(this.foodArray);
  }

  fetchAllFoodItems() {
    const observer = {
      next: (result: Food[]) => {
        this.foodArray = result;
        console.log(this.foodArray);
      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Food[]> = this.service.getFoodList();
    observable.subscribe(observer);
  }

  addToFavourite(foodItem: Food) {
    alert('Food is add from favourite list');
    console.log(foodItem);
    
    const observer = {
      next: (result: Food) => {
        this.addedFood = result;
       
      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Food> =
      this.service.addToFavourite(foodItem);
    observable.subscribe(observer);
  }

}
