import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login/login.component';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { TwoFactorAuthenticationComponent } from './two-factor-authentication/two-factor-authentication.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EmailFormComponent } from './forgetPassword/email-form/email-form.component';
import { PasswordFormComponent } from './forgetPassword/password-form/password-form.component';
import { LoginLayoutComponent } from './login-layout/login-layout.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { MobileVersionComponent } from './mobile-version/mobile-version.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    LoginComponent,
    
    ActivateAccountComponent,
    TwoFactorAuthenticationComponent,
    EmailFormComponent,
    PasswordFormComponent,
    LoginLayoutComponent,
    FooterComponent,
    HeaderComponent,
    MobileVersionComponent,
    
    
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
    RouterModule
  ]
})
export class LoginModule { }
