import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { user } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/userService/user.service';
import { mustMatch } from 'src/app/shared/validators/mustMatchValidator';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-password-form',
  templateUrl: './password-form.component.html',
  styleUrls: ['./password-form.component.css']
})
export class PasswordFormComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService, private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute, private userService: UserService) { }

  passwordPattern = "(?=.*[a-z])(?=.*[A-Z]).{8,}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  token!: any;
  email!: any;
  currentUser!: user


  ngOnInit(): void {
    this.token = this.activatedRoute.snapshot.params.id;
    const formData = new FormData();
    this.userService.getUserFromToken(this.token).subscribe(data => {
      this.email = data.email;
    
      formData.append('email', this.email);
      formData.append('token', this.token);
  
      this.loginService.checkUserPasswordRequestToken(formData).subscribe(data => {
        console.log(data);
  
        if (data == false) {
          this.router.navigate(['login']);
  
        }
  
      })

    })
   

  }

  changePasswordForm = this.formBuilder.group({
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

  get f() { return this.changePasswordForm.controls; }



  changePassword() {

    const formDataPassword = new FormData();
    formDataPassword.append('password', this.changePasswordForm.value.password);
    formDataPassword.append('token', this.token);
    this.userService.changePasswordUser(formDataPassword).subscribe(data => {
      if(data==true)
      this.router.navigate(['login']);
      else
      alert("Password already used Try another one ");

    });
  }


}
