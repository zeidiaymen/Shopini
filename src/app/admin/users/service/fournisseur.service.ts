import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Fournisseur } from 'src/app/shared/models/fournisseur';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {

  baseUrl = environment.url;
  fournisseurController=environment.fournisseurController;
  

  constructor(private http: HttpClient) {

  }
  addFournisseur(formData: any) {
    return this.http.post(this.fournisseurController + 'addFournisseur', formData);

  }

  geFournisseurs() {

    return this.http.get<[Fournisseur]>(this.fournisseurController+"getFournisseurs")
  }
  
}
