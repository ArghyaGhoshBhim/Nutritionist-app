
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { FavouriteModule } from './modules/favourite/favourite.module';
import { FooditemsModule } from './modules/fooditems/fooditems.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthenticationInterceptor } from './request.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FavouriteModule,
    FooditemsModule ,
    AuthenticationModule 
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthenticationInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
