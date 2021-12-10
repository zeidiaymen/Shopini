import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgxWheelComponent, TextAlignment, TextOrientation } from 'ngx-wheel'
@Component({
  selector: 'app-wheel-of-fortune',
  templateUrl: './wheel-of-fortune.component.html',
  styleUrls: ['./wheel-of-fortune.component.css']
})
export class WheelOfFortuneComponent implements OnInit {
  constructor(private r : Router) { }

  @ViewChild(NgxWheelComponent, { static: false }) wheel!:NgxWheelComponent;

  seed = [...Array(12).keys()]
  idToLandOn: any;
  items: any[]=[];
  textOrientation: TextOrientation = TextOrientation.HORIZONTAL
  textAlignment: TextAlignment = TextAlignment.OUTER

  ngOnInit(){
    this.idToLandOn = this.seed[Math.floor(Math.random() * this.seed.length)];
    const colors = ['#FF0000', '#000000']
    this.items = this.seed.map((value) => ({
      fillStyle: colors[value % 2],
      text: `Prize ${value}`,
      id: value,
      textFillStyle: 'white',
      textFontSize: '16'
    }))
  }
  reset() {
    this.wheel.reset()
  }
  prizes = ['Iphone11','Ticket 1d', 'Pc gamer', 'Nothing','Iphone11','Ticket 1d', 'Pc gamer', 'Nothing','Iphone11','Ticket 1d', 'Pc gamer', 'Nothing'];
  before() {
    alert('Your wheel is about to spin')
  
  }

  async spin(prize:any) {
    this.idToLandOn = prize
    await new Promise(resolve => setTimeout(resolve, 0));
    this.wheel.spin()
    console.log (prize)
  }

  after() {
    alert('You Win a '+this.prizes[this.idToLandOn])
    localStorage.setItem('price',this.prizes[this.idToLandOn]);
   
    console.log('reload')
this.r.navigateByUrl('dist/home').then (e => { 
window.location.reload()});
}

}