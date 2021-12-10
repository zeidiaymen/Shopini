import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NotFoundComponent } from './not-found/not-found.component';


import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';


const routes: Routes = [
{
  path:'dashboard',component:DashboardComponent
},
  /* {
     path:'productM',
     loadChildren: ()=>import('./product/product.module').then(m=>m.ProductModule )

   },*/
  /*{path:"home",component:AdminLayoutComponent,
    children:[
      {path: 'products', component: ProductListComponent}
    ]},*/
  { path: "listReclamation", component: ListReclamationComponent },
  

  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
  },


  {
    path: 'productM',
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule)
  },

  {path:"**",component:NotFoundComponent},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
