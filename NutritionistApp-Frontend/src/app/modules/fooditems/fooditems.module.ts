import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FooditemsRoutingModule } from './fooditems-routing.module';
import { ListFooditemsComponent } from './component/list-fooditems/list-fooditems.component';
import { AuthenticationModule } from '../authentication/authentication.module';


@NgModule({
  declarations: [
    ListFooditemsComponent
  ],
  imports: [
    CommonModule,
    FooditemsRoutingModule,
    AuthenticationModule
  ]
})
export class FooditemsModule { }
