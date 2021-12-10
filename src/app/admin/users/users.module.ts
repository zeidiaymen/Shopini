import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { AddFournisseurComponent } from './add-fournisseur/add-fournisseur.component';
import { AddLivreurComponent } from './add-livreur/add-livreur.component';
import { ChangeLivreurComponent } from './change-livreur/change-livreur.component';
import { ListLivreurComponent } from './list-livreur/list-livreur.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { LogOutComponent } from './log-out/log-out.component';
import { ListFournisseurComponent } from './list-fournisseur/list-fournisseur.component';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListClientComponent } from './list-client/list-client.component';


@NgModule({
  declarations: [
    AddAdminComponent,
    AddLivreurComponent,
    AddFournisseurComponent,
    AddAdminComponent,
    ChangeLivreurComponent,
    ListLivreurComponent,
    LogOutComponent,
    ListFournisseurComponent,
    ListAdminComponent,
    ListClientComponent,
    
  ],
  exports: [
    AddAdminComponent,
    AddLivreurComponent,
    AddFournisseurComponent,
    AddAdminComponent,
    ChangeLivreurComponent,
    ListLivreurComponent
],
  imports: [
    CommonModule,
    UsersRoutingModule,
    NgxCaptchaModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule

  ]
})
export class UsersModule { }
