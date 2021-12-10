import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/userService/user.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginResult="";
  divLoginShow=false;

  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private router: Router,private userService:UserService) { }

  ngOnInit(): void {
  }


  loginForm = this.formBuilder.group({

    email: ['', [
      Validators.required,

    ]],
    password: ['', [
      Validators.required,
    ]],


  });



  get f() { return this.loginForm.controls; }

  login() {

    const formData = new FormData();
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    this.loginService.getToken(email, password).subscribe(data => {
      if (data == "False"){
        this.divLoginShow=true;
        this.loginResult="Verif your informations";
      }      
      else if (data == "Not Verified")
      { this.divLoginShow=true;
        this.loginResult="Your account is not verified Please check your email"
      }      
      else {

        let user:any;
        let token=data.toString();
        user=this.userService.getUserFromToken(data.toString()).subscribe(userFromToken=>{
          let role=userFromToken.role;
          

          
          if(role=="CLIENT")
          {
            if(userFromToken.twoFactorAuthentication)
            {
              this.router.navigate(['twoFactorAuthentication/'+token]);

            }
            else{
              localStorage.setItem('token', token);
              localStorage.setItem('role',role);
              this.router.navigate(['/client/clientProfil']);
            }

            }
          else if (role="ADMIN"){
            localStorage.setItem('token', token);
            localStorage.setItem('role',role);

              this.router.navigate(['/admin']);
          }
          else
          console.log(role);
        });
        
        
      }



    })



  }



}
