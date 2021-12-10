import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Client } from '../models/client';
import { user } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {


   }

   baseUrl = environment.url;
   clientController = environment.clientController;

   addClient(formData: any) {
    return this.http.post(this.clientController + 'addClient', formData);
  }

   getUserFromToken(token:any)
  {
    return this.http.get<user>(this.baseUrl+"getUserByToken?token="+token);

  }

  updateUser(formData: any) {
    return this.http.post(this.baseUrl + "updateUser",formData);
  }
  updateUserPicture(formData: any,token:String) {
    return this.http.post(this.baseUrl + "updateUserPicture?token="+token,formData);
  }
  twoFactorAuthenticate(formData:any) {

    return this.http.post(this.baseUrl+"twoFactorAuthenticate",formData)
  }

  changePasswordUser(formData: any) {
    return this.http.post(this.baseUrl + "changePasswordUser",formData);
  }

  
  getClients() {

    return this.http.get<Client[]>(this.clientController+"getClients")
  }
  verifUserOrNot(formData:any) {

    return this.http.post(this.baseUrl+"verifUserOrNot",formData)
  }

}
