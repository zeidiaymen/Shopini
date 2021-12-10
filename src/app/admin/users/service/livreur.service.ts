import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Livreur } from 'src/app/shared/models/livreur';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LivreurService {

  baseUrl = environment.url;
  livreurController=environment.livreurController;
  

  constructor(private http: HttpClient) {

  }
  addLivreur(formData: any) {
    return this.http.post(this.livreurController + 'addLivreur', formData);

  }

  getLivreurs() {

    return this.http.get<Livreur[]>(this.livreurController+"getLivreurs")
  }
 

  changeLivreur(formData:any) {

    return this.http.post(this.livreurController+"changeLivreur",formData)
  }

  
}
