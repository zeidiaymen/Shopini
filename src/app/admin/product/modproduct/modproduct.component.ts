import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import { Product } from 'src/app/shared/models/product';

@Component({
  selector: 'app-modproduct',
  templateUrl: './modproduct.component.html',
  styleUrls: ['./modproduct.component.css']
})
export class ModproductComponent implements OnInit {
  product=new Product();

  constructor(private productService:ProductService,private  activatedroute: ActivatedRoute,private  route:Router) { }

  ngOnInit(): void {
    this.productService.getProductById(this.activatedroute.snapshot.params.idp).subscribe((d) => {
      //console.log(d);
      this.product = d;
  //    console.log(this.product);
    })
  }

    modproduct(){
   // console.log(f);
   //   console.log(this.product);
      this.productService.updateproduct(this.product).subscribe(()=>{
          console.log("good ");
          this.route.navigateByUrl('/productM');
        },
        (error )=>{
          console.log("error");
        },
        ()=>{console.log("complete");} );

    }



}
