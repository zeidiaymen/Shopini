import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { MonitoringDeliveryComponent } from './monitoring-delivery/monitoring-delivery.component';
import { MapComponent } from './map/map.component';
import { CommentsComponent } from './comments/comments.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { FormComponent } from 'src/app/shared/GenericForm/form/form.component';
import { CartComponent } from './cart/cart.component';
import { InfoComponent } from './info/info.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StickyNavModule } from 'ng2-sticky-nav';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { ClientModComponent } from './client-mod/client-mod.component';
import { ClientProfileModule } from './client-profile/client-profile.module';

@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    MonitoringDeliveryComponent,
    MapComponent,
    CommentsComponent,
    AboutUsComponent,
    ContactComponent,
    FormComponent,
    CartComponent,
    InfoComponent,
    ClientModComponent,
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    LeafletModule,
    NgbModule,
    StickyNavModule,
    ZXingScannerModule ,
  ClientProfileModule ],
})
export class ClientModule {}
