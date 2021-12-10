import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Facture } from '../Models/Facture';

@Injectable({
  providedIn: 'root',
})
export class FactureService {
  constructor(private http: HttpClient) {}

  PostFacture(id: any, data: any) {
    return this.http.post('/api/postFacture/' + id, data, {
      responseType: 'arraybuffer',
    });
  }
  getCountClientFacture(id: any) {
    return this.http.get('/api/getCountFact/' + id);
  }
  getFactureByQR(id: any) {
    return this.http.get<Facture>('/api/QRCodeINFO/' + id);
  }
  PaypalPayement(id: any) {
    return this.http.get('/api/Payement/' + id, { responseType: 'text' });
  }

  getFactureByID(id: any) {
    return this.http.get<Facture>('/api/getFactureByID/' + id);
  }
}
