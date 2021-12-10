import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LivreurRoutingModule } from './livreur-routing.module';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MrouterComponent } from './mrouter/mrouter.component';
import { NgxWheelComponent, NgxWheelModule } from 'ngx-wheel';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { WheelOfFortuneComponent } from './wheel-of-fortune/wheel-of-fortune.component';


@NgModule({
  declarations: [
    WheelOfFortuneComponent,
    HomeComponent,
    NavbarComponent,
    MrouterComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    LivreurRoutingModule,
    NgxWheelModule,
    RouterModule,
    HttpClientModule,
    

  ]
  })
export class LivreurModule { }
