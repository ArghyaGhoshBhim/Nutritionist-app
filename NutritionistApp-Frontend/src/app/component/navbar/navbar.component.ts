import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/service/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private authService:AuthenticationService) { }

  ngOnInit(): void {
  }

  showLogOut():boolean{
    return this.authService.isUserLoggedIn();
  }
  logOut(){
    this.authService.removeUserNameAndToken();
  }
 

}
