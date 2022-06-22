import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from 'src/app/guards/login.guard';
import { ListFooditemsComponent } from './component/list-fooditems/list-fooditems.component';

const routes: Routes = [
  {
    path:"home",
    component:ListFooditemsComponent,
    canActivate:[LoginGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FooditemsRoutingModule { }
