import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HousingListComponent } from './housing-list/housing-list.component';
import { HousingDetailsComponent } from './housing-details/housing-details.component';
import { HousingCenterComponent } from './housing-center/housing-center.component';
import { HousingFormComponent } from './housing-form/housing-form.component';


const routes: Routes = [
  { path: '', component: HousingCenterComponent, children: [
      { path: '', component: HousingListComponent },
      { path: '', component: HousingDetailsComponent },
      { path: '', component: HousingFormComponent}
  ] },
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class HousingsRoutingModule { }
