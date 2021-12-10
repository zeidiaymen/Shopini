import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Delivery } from '../Models/delivery';

@Injectable({
  providedIn: 'root',
})
export class DeliveryServService {
  constructor(private http: HttpClient) {}
  getDeliveries(id:any) {
    return this.http.get('/api/getDeliveryByIdClient/'+id);
  }
  getLocation(ip: String) {
    return this.http.get('/api/getLocation/' + ip, {
      responseType: 'text',
    });
  }
  postDelivery(d: Delivery, id: any) {
    return this.http.post('/api/PostDelivery/' + id, d);
  }
}
