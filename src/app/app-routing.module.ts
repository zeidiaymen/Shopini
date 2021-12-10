import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayoutComponent } from './admin/admin-layout/admin-layout.component';
import { LoginForTestComponent } from './login-for-test/login-for-test.component';
import { LoginLayoutComponent } from './login/login-layout/login-layout.component';
import { RegisterComponent } from './register/register.component';
const routes: Routes = [
  { path: 'welcome', component: LoginForTestComponent, },
  
    { path: '', redirectTo: 'welcome', pathMatch: 'full' },
  
  {
    path: 'client',
    //canActivate:[ClientGuard],
    loadChildren: () =>
      import('./Client/client/client.module').then((m) => m.ClientModule),
  },
  {
    path: "login",component:LoginLayoutComponent,
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
  },
  
  {
    path:'register',component:RegisterComponent
  },
  {
    path: 'livreur',
//canActivate:[LivreurGuard],
    loadChildren: () =>
      import('./Livreur/livreur/livreur.module').then((m) => m.LivreurModule),
  },{
    path: 'admin', component: AdminLayoutComponent,
  //  canActivate:[AdminGuard],
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },

 

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
