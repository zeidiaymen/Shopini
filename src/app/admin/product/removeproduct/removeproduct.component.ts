import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-removeproduct',
  templateUrl: './removeproduct.component.html',
  styleUrls: ['./removeproduct.component.css']
})
export class RemoveproductComponent implements OnInit {

  constructor(private productService:ProductService,private  route:Router,
              private  activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
   // console.log(this.activatedroute.snapshot.params.idp);
      this.productService.deleteproduct(this.activatedroute.snapshot.params.idp).subscribe(()=>{
          console.log("good ");
          this.route.navigateByUrl('/productM');
        },
        (error )=>{
          console.log("error");
        },
        ()=>{console.log("complete");} );

  }



}
