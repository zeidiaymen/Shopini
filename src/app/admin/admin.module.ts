import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import {SidebarComponent} from "./sidebar/sidebar.component";
import {TopbarComponent} from "./topbar/topbar.component";
import { FooterComponent } from './footer/footer.component';
import { ProductModule } from './product/product.module';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AdminLayoutComponent} from "./admin-layout/admin-layout.component";
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { UpdateReclamationComponent } from './reclamation/update-reclamation/update-reclamation.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SidebarComponent,
    TopbarComponent,
    FooterComponent,
    AdminLayoutComponent,
    ListReclamationComponent,
    UpdateReclamationComponent,
    NotFoundComponent,
    DashboardComponent
    
  ],
    exports: [
        SidebarComponent,
        TopbarComponent,
        FooterComponent,
      AdminLayoutComponent
    ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ProductModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
  
    
    
    

    
  
    
  ]
})
export class AdminModule { }
