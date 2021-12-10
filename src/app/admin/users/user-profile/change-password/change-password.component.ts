import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/userService/user.service';
import { mustMatch } from 'src/app/shared/validators/mustMatchValidator';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private router:Router ,private service: UserService, private formBuilder: FormBuilder) { }
  passwordPattern = "(?=.*[a-z])(?=.*[A-Z]).{8,}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  token!:any;
  resultChangePassword!:any;

  ngOnInit(): void {
    this.resultChangePassword=true;
  }


  changePasswordClientForm = this.formBuilder.group({
    password: ['', [
      Validators.required,
      Validators.pattern(this.passwordPattern)
    ]],
    confirmPassword: ['',
      Validators.required,
    ],
    
    reCaptcha: ['', Validators.required]

  }, {
    validator: mustMatch('password', "confirmPassword"),

  });
  get f() { return this.changePasswordClientForm.controls; }


  changePasswordClient(){
    this.token=localStorage.getItem('token');
    const formData = new FormData();
    formData.append('password', this.changePasswordClientForm.value.password);
    formData.append('token', this.token);
    this.service.changePasswordUser(formData).subscribe(data => {
      console.log(data);

      if(data==true)
      this.router.navigate(['userProfile']);
      else
      this.resultChangePassword=false;

    });
    }


}
