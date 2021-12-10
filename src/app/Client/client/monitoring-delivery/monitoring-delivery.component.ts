import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Delivery } from '../Models/delivery';
import { DeliveryServService } from '../Services/delivery-serv.service';
import { IpStoreService } from '../Services/ip-store.service';
import * as L from 'leaflet';
import { Subscription, interval } from 'rxjs';
class countdown {
  hour!: number[];
  minute!: number[];
}
@Component({
  selector: 'app-monitoring-delivery',
  templateUrl: './monitoring-delivery.component.html',
  styleUrls: ['./monitoring-delivery.component.css'],
})
export class MonitoringDeliveryComponent implements OnInit, AfterViewInit {
  constructor(private ds: DeliveryServService, private ipS: IpStoreService) {}
  distance: any;
  show1 = true;
  idCl = Number(localStorage.getItem('id'));
  subscription!: Subscription;
  get(event: any) {
    this.show = false;
    this.show1 = event;
  }
  //  tims!: fakeDate

  list: Delivery[] = [];
  tim: any[] = [];
  dateReminder: any[] = [];
dateFixer:any[]=[]
  ngOnInit(): void {
    //

    this.ds.getDeliveries(localStorage.getItem('id')).subscribe((x: any) => {
      this.list = x;
      this.list.forEach((e) => {
        var d = new Date(e.estimatedTime).getTime() - new Date().getTime();

        var h = Math.floor(d * 0.00000027778);
        var m = Math.floor((d * 0.00000027778 - h) * 60);

        var s = Math.ceil(((d * 0.00000027778 - h) * 60 - m) * 60);

        this.tim.push(h + 'h : ' + m + 'm : ' + s + 's');
        this.dateReminder.push(h + m + s);
        this.dateFixer.push(h)
      });

      console.log(this.distance);
    });
  }
  Long!: String;
  Lat!: String;
  show = false;
  getLocalion(ip: any) {
    this.ipS.getIp(ip).subscribe((x: any) => {
      console.log(x.ipaddress);
      this.ds.getLocation(x.ipaddress).subscribe((d: String) => {
        console.log(d);
        this.Lat = d.substring(d.indexOf('latitude:') + 9, d.indexOf(',long'));
        this.Long = d.substring(d.indexOf('longitude:') + 10, d.indexOf('}'));

        console.log(this.Lat);
        this.show = true;
        this.show1 = false;
      });
    });
  }
 

  ngAfterViewInit(): void {}
//


}
