import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { CartComponent } from './cart/cart.component';
import { ClientModComponent } from './client-mod/client-mod.component';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { InfoComponent } from './info/info.component';
import { MonitoringDeliveryComponent } from './monitoring-delivery/monitoring-delivery.component';

const routes: Routes = [
  {
    path: 'client',
    component: ClientModComponent,
    children: [
      { path: '', component: ClientModComponent },
      { path: 'delivery', component: MonitoringDeliveryComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'info/:id/:info', component: InfoComponent },
      { path: 'home', component: HomeComponent },
      { path: 'aboutUs', component: AboutUsComponent },
      {
        path: 'cart',
        component: CartComponent,
      },
      {
        path: 'clientProfil',
        loadChildren: () => import('./client-profile/client-profile.module').then(m => m.ClientProfileModule)
      },
    ],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientRoutingModule {}
