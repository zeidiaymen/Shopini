import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/shared/models/product';
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-main-product',
  templateUrl: './main-product.component.html',
  styleUrls: ['./main-product.component.css']
})
export class MainProductComponent implements OnInit {
  //ListProduct!:Product[];
  ListProduct!:Product[];
  produit=new Product();
  constructor(private productService:ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
   // this.addproduct(this.produit);
  }

  getAllProducts(){
    this.productService.getAllProducts().subscribe((d)=>{
      //console.log(d);
      this.ListProduct=d;
    //  console.log(this.ListProduct[0]);
    })
  }





}
