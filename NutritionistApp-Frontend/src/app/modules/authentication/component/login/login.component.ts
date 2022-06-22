import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { observable, Observable } from 'rxjs';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  myform!:FormGroup;
  usernameCntrl!:FormControl;
  passwordCntrl!:FormControl;

  errMsg:string|undefined;

  constructor(private builder:FormBuilder, private authService:AuthenticationService, private roter:Router) { 
    this.usernameCntrl=builder.control('',[Validators.required]);
    this.passwordCntrl=builder.control('',[Validators.required]);
    this.myform=builder.group({
      username:this.usernameCntrl,
      password:this.passwordCntrl,
    })
  }

  ngOnInit(): void {
  }


  
  loginUser(){
    console.log("this is loginUser");
    if (!this.myform.valid) {
      this.myform.markAllAsTouched();
      return;
    }

    const username=this.usernameCntrl.value;
    const password =this.passwordCntrl.value;
    console.log(username, ",",password);
    const observer={
      next:(token:string)=>{
        console.log("token: ", token);
        this.authService.saveToken(username, token);
        this.roter.navigate(["/home"]);
        
      },
      error:(err:Error)=>{
        this.errMsg=err.message;
        console.log("error msg ", this.errMsg);
      }
    }


  const observable:Observable<string>= this.authService.login(username, password);
  observable.subscribe(observer);
  




  }


  //Validation of username..... 

  isTouchedOrDirty(control: FormControl): boolean {
    return control.touched || control.dirty;
  }

  isUserameCtrlRequireValid() {
    const touchedOrDirty=this.isTouchedOrDirty(this.usernameCntrl);
    if (!touchedOrDirty) {
      return true;
    }
    return !this.usernameCntrl.errors?.['required'];
  }


  // Validation of Password......
  isPasswordCtrlRequireValid(){
    const touchedOrDirty=this.isTouchedOrDirty(this.passwordCntrl);
    if(!touchedOrDirty){
      return true;
    }
    return !this.passwordCntrl.errors?.['required'];

  }

}
