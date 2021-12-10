import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductInOrder } from '../Models/ProductInORder';

@Injectable({
  providedIn: 'root',
})
export class ProdInOrderService {
  constructor(private http: HttpClient) {}

  getAllProdsInORder(id:any) {
    return this.http.get<ProductInOrder[]>('/api/getProdByIdClient/'+id);
  }
  postProdInOrder(prod: any, id: any) {
    return this.http.post<ProductInOrder>('/api/ProdInOrder/' + id, prod);
  }
  deleteProdInOrder(id: any) {
    return this.http.delete('/api/deleteProdInOrder/' + id);
  }
  deleteAllProds(id: any) {
    return this.http.delete('/api/deleteAllProdInOrder/' + id);
  }
  getNbProdInOrder() {
    return this.http.get('/api/getNbProdInOrder');
  }
}
