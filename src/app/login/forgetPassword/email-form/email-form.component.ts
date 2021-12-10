import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-email-form',
  templateUrl: './email-form.component.html',
  styleUrls: ['./email-form.component.css']
})
export class EmailFormComponent implements OnInit {

  constructor(private loginService: LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  email = new FormControl('', [
    Validators.email,
    Validators.required]
  );

  



forgetPasswordRequest() {
  const formData = new FormData();
  formData.append('email', this.email.value);
  this.loginService.forgetPasswordRequest(formData).subscribe(data => {
    console.log(data);
    alert("Verif your inbox");
    this.router.navigate(['login']);    
  });
}


}
