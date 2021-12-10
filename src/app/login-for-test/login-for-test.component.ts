import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-for-test',
  templateUrl: './login-for-test.component.html',
  styleUrls: ['./login-for-test.component.css']
})
export class LoginForTestComponent implements OnInit {

  constructor(private r:Router) { 
    localStorage.setItem('role','ADMIN');
  }
ss()
{
  this.r.navigateByUrl('/login')
}
  ngOnInit(): void {
  }
  redToReg()
  {
    this.r.navigateByUrl('/register')
  }
}
