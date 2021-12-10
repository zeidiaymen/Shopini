import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddAdminComponent } from './add-admin/add-admin.component';
import { AddFournisseurComponent } from './add-fournisseur/add-fournisseur.component';
import { AddLivreurComponent } from './add-livreur/add-livreur.component';
import { ChangeLivreurComponent } from './change-livreur/change-livreur.component';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListClientComponent } from './list-client/list-client.component';
import { ListFournisseurComponent } from './list-fournisseur/list-fournisseur.component';
import { ListLivreurComponent } from './list-livreur/list-livreur.component';
import { LogOutComponent } from './log-out/log-out.component';


const routes: Routes = [


  {path:'addLivreur',component:AddLivreurComponent,
  },  
  
  { path: "addAdmin", component: AddAdminComponent },
  {path:'addFournisseur',component:AddFournisseurComponent},
  {path:'logOut',component:LogOutComponent},
  {path:'listAdmin',component:ListAdminComponent},
  {path:'listFournisseur',component:ListFournisseurComponent},
  {path:'listClient',component:ListClientComponent},


  {path:'listLivreur',component:ListLivreurComponent,
  children: [
    {
      path: 'changeLivreur', // child route path
      component: ChangeLivreurComponent, // child route component that the router renders
    }]
},

{
  path: 'userProfile',
  loadChildren: () => import('./user-profile/user-profile.module').then(m => m.UserProfileModule)
},





];

@NgModule({
  imports: [RouterModule.forChild(routes),  ],
  exports: [RouterModule
  
  
  ]
})
export class UsersRoutingModule { }
