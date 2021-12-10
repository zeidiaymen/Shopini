import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {

  constructor(private loginService: LoginService,private router:Router, private activatedRoute: ActivatedRoute) {
    this.activationToken=this.activatedRoute.snapshot.params.id;
   }
   activationToken!:any;

  ngOnInit(): void {
    this.loginService.activateAccount(this.activationToken).subscribe(data=>{
      console.log(data);
      if(data)
      {
        this.router.navigate(['login']);
        alert("VERIFIED");   
      }
      else {
        this.router.navigate(['login']);
        alert("Verif your email again")
      }
    })

  }

}
