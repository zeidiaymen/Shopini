import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Models/Client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private http: HttpClient) {}
  getClientById(id: any) {
    return this.http.get<Client>('/api/get/' + id);
  }
}
