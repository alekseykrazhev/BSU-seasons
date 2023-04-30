import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HousingListComponent } from './housing-list/housing-list.component';
import { HousingDetailsComponent } from './housing-details/housing-details.component';
import { AppComponent } from '../app.component';
import { HousingCenterComponent } from './housing-center/housing-center.component';


const routes: Routes = [
  { path: '', component: HousingCenterComponent, children: [
      { path: '', component: HousingListComponent },
      { path: ':id', component: HousingDetailsComponent },
  ] },
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class HousingsRoutingModule { }
