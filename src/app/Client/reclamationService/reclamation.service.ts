import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {
baseUrl=environment.url;
  constructor(private http:HttpClient) { }
  addReclamation(recl:any,idP:any,idUser:any){
    return this.http.post(this.baseUrl+"addReclamation?idProduit="+idP+"&idUser="+idUser,recl)
  }

  listRelamations(){
    return this.http.get(this.baseUrl+"reclamations")
  }
  
  update(recl:any,idP:any,idUser:any){
    return this.http.put(this.baseUrl+"updateReclamation?idProduit="+idP+"&idUser="+idUser,recl)
  }
  delete(id:any){
    return this.http.delete(this.baseUrl+"deleteReclamation?id="+id)
  }
}
