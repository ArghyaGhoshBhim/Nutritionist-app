import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, observable } from 'rxjs';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  myform!:FormGroup;
  usernameCntrl!:FormControl;
  passwordCntrl!:FormControl;

  constructor(builder:FormBuilder, private authService:AuthenticationService, private router:Router) {
    this.usernameCntrl=builder.control('',[Validators.required, Validators.minLength(4)]);
    this.passwordCntrl=builder.control('',[Validators.required, Validators.minLength(4)]);
    
    this.myform=builder.group({
      username:this.usernameCntrl,
      password:this.passwordCntrl,
    })
   }

  ngOnInit(): void {
  }


  registerUser(){
    console.log("this is registerUser");
    if (!this.myform.valid) {
      this.myform.markAllAsTouched();
      return;
    }

    const username=this.usernameCntrl.value;
    const password=this.passwordCntrl.value;
    console.log(username);
    console.log(password);
    const observer={
      next:(result:any)=>{
        this.router.navigate(['login']);
      },
      error:(error:Error)=>{
        alert("Couldn't register "+error.message);
      }

    }
    const observable:Observable<any>=this.authService.register(username,password);
    observable.subscribe(observer);


    
  }


  //Validation Name
  isTouchedOrDirty(control: FormControl): boolean {
    return control.touched || control.dirty;
  }
  
 


  //Validation of username..... 
 
  isUserameCtrlRequireValid() {
    const touchedOrDirty=this.isTouchedOrDirty(this.usernameCntrl);
    if (!touchedOrDirty) {
      return true;
    }
    return !this.usernameCntrl.errors?.['required'];
  }


  isUserameCtrlMinLengthValid() {
    const touchedOrDirty=this.isTouchedOrDirty(this.usernameCntrl);
    if (!touchedOrDirty) {
      return true;
    }
    return !this.usernameCntrl.errors?.['minlength'];
  }


  // Validation of Password......
  isPasswordCtrlRequireValid(){
    const touchedOrDirty=this.isTouchedOrDirty(this.passwordCntrl);
    if(!touchedOrDirty){
      return true;
    }
    return !this.passwordCntrl.errors?.['required'];

  }


  isPasswordCtrlMinLengthValid(){
    const touchedOrDirty=this.isTouchedOrDirty(this.passwordCntrl);
    if(!touchedOrDirty){
      return true;
    }
    return !this.passwordCntrl.errors?.['minlength'];
  }

}
