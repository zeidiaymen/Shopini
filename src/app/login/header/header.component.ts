import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { user } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/userService/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router,private userService: UserService) {}
   

  token!:any;
  currentUser!: user
  ngOnInit(): void {
    this.token = localStorage.getItem('token');
    this.userService.getUserFromToken(this.token).subscribe(data => {
      
      this.currentUser = data;
      
    })
  }

}
