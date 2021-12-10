import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  @Input() long!: String;
  @Input() larg!: String;
  @Output() not = new EventEmitter<any>();

  constructor() {}
  send() {
    this.not.emit(true);
    //   this.router.navigateByUrl('delivery');
  }
  ngOnInit(): void {
    const myfrugalmap = L.map('frugalmap').setView(
      [Number(this.long), Number(this.larg)],
      12
    );

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: 'Frugal Map',
    }).addTo(myfrugalmap);
    const myIcon = L.icon({
      iconUrl:
        'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png',
    });
    L.marker([Number(this.long), Number(this.larg)], { icon: myIcon })
      .bindPopup("You're delivery is here !")
      .addTo(myfrugalmap)
      .openPopup();
  }
}
