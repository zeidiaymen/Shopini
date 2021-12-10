import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientProfileRoutingModule } from './client-profile-routing.module';
import { ChangeProfilComponent } from './change-profil/change-profil.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxCaptchaModule } from 'ngx-captcha';


@NgModule({
  declarations: [
    ChangeProfilComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    ClientProfileRoutingModule,
    NgxCaptchaModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ClientProfileModule { }
