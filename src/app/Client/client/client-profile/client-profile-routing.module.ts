import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ChangeProfilComponent } from './change-profil/change-profil.component';

const routes: Routes = [


  {path:'',component:ChangeProfilComponent},  
  
  { path: "changePassword", component: ChangePasswordComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientProfileRoutingModule { }
