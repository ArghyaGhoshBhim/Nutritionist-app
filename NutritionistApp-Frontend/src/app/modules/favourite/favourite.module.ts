import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FavouriteRoutingModule } from './favourite-routing.module';
import { ListFavouriteItemComponent } from './component/list-favourite-item/list-favourite-item.component';


@NgModule({
  declarations: [
    ListFavouriteItemComponent
  ],
  imports: [
    CommonModule,
    FavouriteRoutingModule
  ],
  exports:[
    FavouriteRoutingModule
  ]
})
export class FavouriteModule { }
