import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FactStatsService {

  constructor(private http:HttpClient) { }
  getDelivery()
  {
    return this.http.get<any[]>('http://localhost:8088/getDeliveries')
  }
}
