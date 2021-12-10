import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/shared/userService/user.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-two-factor-authentication',
  templateUrl: './two-factor-authentication.component.html',
  styleUrls: ['./two-factor-authentication.component.css']
})
export class TwoFactorAuthenticationComponent implements OnInit {

  constructor(private userService :UserService,private loginService: LoginService,private router:Router, private activatedRoute: ActivatedRoute) {
    this.token=this.activatedRoute.snapshot.params.id;
   }
  token!:any;
  verificationCode!:any;
  tel = "";
  tryNumber=0;
  phoneNumber!:any;
  resultVerificationCode:any;



  ngOnInit(): void {
    this.resultVerificationCode=true;
    this.token=this.activatedRoute.snapshot.params.id;
    this.loginService.sendSmsTwoFactorAuthentication(this.token).subscribe(data=>{   
       
    this.verificationCode=data;
    console.log(this.verificationCode);

  });
  this.userService.getUserFromToken(this.token).subscribe(data=>{

    this.phoneNumber=data.tel;
    this.phoneNumber=this.convertPhoneNumber(this.phoneNumber);


  })

}

 
  convertPhoneNumber(phoneNumber:any){

    let newPhoneNumber="";

    for (let i = 0; i < phoneNumber.length; i++) {
      
      if(i>=phoneNumber.length-3){
        newPhoneNumber+=phoneNumber[i];
      }
      else      
      newPhoneNumber+="*";


      
    }
    return newPhoneNumber;

  }
 



  confirmCode(){

    this.resultVerificationCode=true;
    console.log("hey"+this.verificationCode);

    

      if(this.verificationCode==this.tel){
        localStorage.setItem('token',this.token);
      this.router.navigate(['userProfile']);
    }
    else{
      this.tryNumber++;
      this.resultVerificationCode=false;

    }

    if(this.tryNumber==3)
    { alert("Try again");
      this.router.navigate(['login']);


    }
    
      

  
    

  }

}
