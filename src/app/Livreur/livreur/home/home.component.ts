import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Delivery } from 'src/app/Client/Models/delivery';
import { DeliveryService } from '../Services/delivery.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
ipAddress='';
date = new Date().getTime();  
list:Delivery[]=[];
constructor(private serv:DeliveryService,private http:HttpClient) { 
    localStorage.setItem('id','4');
  }

takeDel(id : any )
{
this.serv.setDelivery((localStorage.getItem('id')),id).subscribe(e=> {
  this.serv.addIP(id,{'ipaddress':this.ipAddress}).subscribe()
  this.serv.getDeliveries().subscribe(e=> {
    this.list=e;
  //console.log(e); 
  })
});

}
  ngOnInit(): void {
this.getIPAddress();
this.serv.getDeliveries().subscribe(e=> {
  this.list=e;
//console.log(e); 
})
}
dev :any = null;
  getIPAddress()
  {
    this.http.get("http://api.ipify.org/?format=json").subscribe((res:any)=>{
      this.ipAddress = res.ip;
    //  console.log(res.ip)
 this.serv.getDeliveryById( (localStorage.getItem('id'))).subscribe (e => {  
  console.log(e)  
  this.dev=e;
  console.log(this.dev)
  this.serv.updateIpAddress(this.ipAddress,e).subscribe()
    },e=> {console.log("this Livreur don't have delivery yet")});
  } )
  }

  setIPAddress()
  {
    this.http.get("http://api.ipify.org/?format=json").subscribe((res:any)=>{
      this.ipAddress = res.ip;
    //  console.log(res.ip)
 this.serv.getDeliveryById( (localStorage.getItem('id'))).subscribe (e => {  
  console.log(e)  
  this.dev=e;
  console.log(this.dev)
  this.serv.addIP(this.ipAddress,e).subscribe()
    },e=> {console.log("this Livreur don't have delivery yet")});
  } )

}


}
