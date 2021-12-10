import {Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {

  constructor(@Inject(DOCUMENT) private document: Document) { }

  ngOnInit(): void {
/*    const head = this.document.getElementsByTagName('head')[0];
    const style = this.document.createElement('link');
    style.rel = 'stylesheet';
    style.href = `back-s1.css`;
    head.appendChild(style);
    style.href = `back-s2.css`;
    head.appendChild(style);*/
    //style.href = `/assets/back/css/sb-admin-2.min.css`;
   // head.appendChild(style);
    //style.href = `https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i`;
   // head.appendChild(style);
  //  var link = document.createElement('link');
    //link.setAttribute('rel', 'stylesheet');
    //link.setAttribute('type', 'text/css');
   // link.setAttribute('href', 'https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i')
   // document.getElementsByTagName('head')[0].appendChild(link)
    //link.setAttribute('href', 'assets/back/vendor/fontawesome-free/css/all.min.css');
  //  document.getElementsByTagName('head')[0].appendChild(link);
    //link.setAttribute('href', 'assets/back/css/sb-admin-2.min.css');
    //document.getElementsByTagName('head')[0].appendChild(link);

  }

}
