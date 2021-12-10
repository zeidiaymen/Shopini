import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainProductComponent} from "./main-product/main-product.component";
import {AddproductComponent} from "./addproduct/addproduct.component";
import {RemoveproductComponent} from "./removeproduct/removeproduct.component";
import {ModproductComponent} from "./modproduct/modproduct.component";
import {AdminLayoutComponent} from "../admin-layout/admin-layout.component";

const routes: Routes = [
  {path:"add" ,component:AddproductComponent },
  {path: 'remove/:idp', component: RemoveproductComponent},
  {path: 'update/:idp', component: ModproductComponent},
  {path:"**",component:MainProductComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
