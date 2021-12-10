import { Injectable } from '@angular/core';
import {  CanActivate, Router } from '@angular/router';
import { UserService } from 'src/app/shared/userService/user.service';

@Injectable({
  providedIn: 'root'
})
export class LivreurGuard implements CanActivate {
  constructor(private userService: UserService,private router: Router) {
  }
  
  guardRole="LIVREUR";
  
  canActivate() {

    let role=localStorage.getItem('role');
    let token=localStorage.getItem('token');

    this.userService.getUserFromToken(token).subscribe(data=>{
      if(!data){
        console.log("token expired");
      localStorage.removeItem('token');
      localStorage.removeItem('role');}
    });

    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }
    else{
      if(role==this.guardRole){
        return true;

      }
      else {
        alert('Access denied');
        return false;
      }
    }
    
    
  

  }

}
