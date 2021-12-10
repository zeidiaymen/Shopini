import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from 'src/app/shared/models/admin';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  baseUrl = environment.url;
  adminController=environment.adminController;
  

  constructor(private http: HttpClient) {

  }
  addAdmin(formData: any) {
    return this.http.post(this.adminController + 'addAdmin', formData);

  }
  getAdmins() {

    return this.http.get<Admin[]>(this.adminController+"getAdmins")
  }
}
