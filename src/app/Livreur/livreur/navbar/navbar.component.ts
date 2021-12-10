import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
tog:boolean=false ;
prix = localStorage.getItem('price')
togs()
{
  this.tog=!this.tog;
  console.log(this.tog)
}
  constructor() {   console.log (localStorage.getItem('price'))
}
delete()
{
  localStorage.removeItem('price')
this.prix =localStorage.getItem('price');
}

  ngOnInit(): void {
  }

}
