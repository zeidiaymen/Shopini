import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class IpStoreService {
  constructor(private http: HttpClient) {}
  getIp(id: number) {
    return this.http.get('/api/getIp/' + id);
  }
}
