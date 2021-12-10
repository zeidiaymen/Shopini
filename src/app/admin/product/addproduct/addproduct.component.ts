import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {Router} from "@angular/router";
import { Product } from 'src/app/shared/models/product';
import { DetailProduit } from 'src/app/shared/models/detailProduit';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {
  product = new Product();
  productdetail = new DetailProduit();
  idf:any;
  constructor(private productService:ProductService,private route:Router) { }

  ngOnInit(): void {
  }
  addproduct(){
    this.product.detailProduit=this.productdetail;
    this.productService.addproduct(this.product,this.idf).subscribe(()=>{
        console.log("good ");
        this.route.navigateByUrl('/productM');
      },
      (error )=>{
        console.log("error");
      },
      ()=>{console.log("complete");} );
  }

}
