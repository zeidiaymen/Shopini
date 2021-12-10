import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log-out',
  templateUrl: './log-out.component.html',
  styleUrls: ['./log-out.component.css']
})
export class LogOutComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');

    this.router.navigate(['/login']);   

  }

}
