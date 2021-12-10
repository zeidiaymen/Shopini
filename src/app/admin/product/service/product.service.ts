import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Product } from 'src/app/shared/models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //private productUrl = `${environment.url}/product`;
  baseurl=environment.url+"produit/" ;
  Products :Product[]=[];


  constructor(private  http:HttpClient) { }

  getAllProducts() :Observable<Product[]>{
    //return this.Products;
    return this.http.get<Product[]>(this.baseurl+"retrieve-all-produits");
  }
  getProductById(idp:any) :Observable<Product>{
    //return this.Products;
    return this.http.get<Product>(this.baseurl+"retrieve-produit/"+idp);
  }

  addproduct(p:Product,idf: any){
return this.http.post(this.baseurl+'add-produit/'+idf,p);
  }
  deleteproduct(idp: any){
    return this.http.delete(this.baseurl+'remove-produit/'+idp);
  }
  updateproduct(p:Product){
    return this.http.put(this.baseurl+'modify-produit/',p);
  }



}
