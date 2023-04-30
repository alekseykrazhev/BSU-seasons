import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HousingCenterComponent } from './housing-center/housing-center.component';
import { HousingListComponent } from './housing-list/housing-list.component';
import { HousingDetailsComponent } from './housing-details/housing-details.component';
import { HousingsRoutingModule } from './housings-routing.module';



@NgModule({
  declarations: [
    HousingCenterComponent,
    HousingListComponent,
    HousingDetailsComponent
  ],
  imports: [
    CommonModule,
    HousingsRoutingModule
  ]
})
export class HousingsModule { }
