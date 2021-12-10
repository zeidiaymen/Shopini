import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { EmailFormComponent } from './forgetPassword/email-form/email-form.component';
import { PasswordFormComponent } from './forgetPassword/password-form/password-form.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [


  {path:'',component:LoginComponent},
  {path:'forgetPasswordEmailForm',component:EmailFormComponent},
  {path:'forgetPasswordPasswordForm/:id',component:PasswordFormComponent},
  {path:'activateAccount/:id',component:ActivateAccountComponent},
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
