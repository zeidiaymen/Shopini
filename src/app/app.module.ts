import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ClientModule } from './Client/client/client.module';
import { LoginForTestComponent } from './login-for-test/login-for-test.component';
import { LivreurModule } from './Livreur/livreur/livreur.module';
import { NgxWheelModule } from 'ngx-wheel';
import { RegisterComponent } from './register/register.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { LoginModule } from './login/login.module';
import { AdminModule } from './admin/admin.module';
@NgModule({
  declarations: [AppComponent, LoginForTestComponent, RegisterComponent],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ClientModule,
    RouterModule,
    LivreurModule,
    NgxWheelModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
LoginModule,
AdminModule ,




],
  providers: [],
  bootstrap: [AppComponent],
  schemas:[CUSTOM_ELEMENTS_SCHEMA],

})
export class AppModule {}
