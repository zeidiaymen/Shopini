import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../shared/userService/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private  clientService:UserService,private router: Router) {

  }

  canActivate(): boolean {
    let token = localStorage.getItem('token');
    this.clientService.getUserFromToken(token).subscribe(data=>{
      if(!data) {
      localStorage.removeItem('token');
      localStorage.removeItem('role');}



    });
    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }
    //could changed

    return true;

  }

}
