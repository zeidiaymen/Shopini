import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { MainProductComponent } from './main-product/main-product.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import {FormsModule} from "@angular/forms";
import {SidebarComponent} from "../sidebar/sidebar.component";
import {TopbarComponent} from "../topbar/topbar.component";
import {FooterComponent} from "../footer/footer.component";
import {AdminModule} from "../admin.module";
import { ModproductComponent } from './modproduct/modproduct.component';
import { RemoveproductComponent } from './removeproduct/removeproduct.component';


@NgModule({
  declarations: [
    MainProductComponent,
    AddproductComponent,
    ModproductComponent,
    RemoveproductComponent

  ],
  imports: [
    CommonModule,
    ProductRoutingModule,
    FormsModule

  ]
})
export class ProductModule { }
