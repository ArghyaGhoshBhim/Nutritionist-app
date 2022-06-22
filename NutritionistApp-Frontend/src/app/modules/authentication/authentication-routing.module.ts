import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { RegistrationComponent } from './component/registration/registration.component';

const routes: Routes = [
  {
    path:'', redirectTo:"login", pathMatch:"full"
  },
  {
    path:'register',
    component:RegistrationComponent
    
 },
 {
    path:'login',
    component:LoginComponent,
 }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }
