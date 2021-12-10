import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Delivery } from 'src/app/Client/Models/delivery';
import { Livreur } from '../Models/livreur';
@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  constructor(private http:HttpClient) { }

getDelivery(id:any)
{
  return this.http.get<Livreur>('/api/get/'+id)
}
setDelivery(id:any,ids:any)
{
return this.http.put('/api/assossiateLivreur/'+id +'/'+ids,null)
}
addIP(id:any,obj:any)
{
  return this.http.post('/api/postIp/'+id,obj);
}
getDeliveryById(id:any)
{
  return this.http.get('/api/getByIdLivreurForDelivery/'+id)
}
updateIpAddress(ip:String,id:any)
{
  return this.http.get('/api/updateIp/'+ip+'/'+id);
}
getDeliveries() {
  return this.http.get<Delivery[]>('/api/getDeliveries');
}
}