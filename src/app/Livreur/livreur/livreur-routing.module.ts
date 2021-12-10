import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MrouterComponent } from './mrouter/mrouter.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WheelOfFortuneComponent } from './wheel-of-fortune/wheel-of-fortune.component';

const routes: Routes = [
  
  {path:'dist',component:MrouterComponent 
  , children: [ 
  {path:'home',component:HomeComponent},
  {
    path:'wheel',component:WheelOfFortuneComponent
  },
  {
    path:'navbar',component:NavbarComponent
  }
  
]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LivreurRoutingModule { }
