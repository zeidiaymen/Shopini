import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.url;

  getToken(email:string,password:string) {
     return this.http.get(this.baseUrl+"authenticate?email="+email+"&password="+password,{responseType: 'text' as 'json'});
    }

    forgetPasswordRequest(email:any) {
      return this.http.post(this.baseUrl+"forgetPasswordRequest",email);
     }

     checkUserPasswordRequestToken(formData:any) {
      return this.http.post(this.baseUrl+"checkUserPasswordRequestToken",formData);
     }

     sendSmsTwoFactorAuthentication(token:any) {
      return this.http.get(this.baseUrl+"sendSmsTwoFactorAuthentication?token="+token);
     }

     activateAccount(activationToken:any) {
      return this.http.get(this.baseUrl+"activateAccount?activationToken="+activationToken);
     }
}
