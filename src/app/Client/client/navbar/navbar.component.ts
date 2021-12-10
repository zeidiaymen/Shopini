import { Component, OnInit } from '@angular/core';
import { ProdInOrderService } from '../Services/prod-in-order.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(private serv: ProdInOrderService) {}
  token!:any;
  currentUser:any ;

  
 ///
  nb: any;
  ngOnInit(): void {
    window.onscroll = function () {
      myFunction();
    };
    this.serv.getNbProdInOrder().subscribe((e) => {
      this.nb = e;
    });
    var navbar = document.getElementById('navnav');
    var sticky = navbar?.offsetTop;

    function myFunction() {
      if (window.pageYOffset >= 200) {
        navbar?.classList.add('sticky');
      } else {
        navbar?.classList.remove('sticky');
      }
    }
  }
}
