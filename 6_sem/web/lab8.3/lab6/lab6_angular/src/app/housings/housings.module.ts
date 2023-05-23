import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HousingCenterComponent } from './housing-center/housing-center.component';
import { HousingListComponent } from './housing-list/housing-list.component';
import { HousingDetailsComponent } from './housing-details/housing-details.component';
import { HousingsRoutingModule } from './housings-routing.module';
import { FormsModule } from '@angular/forms';
import { HousingFormComponent } from './housing-form/housing-form.component';



@NgModule({
  declarations: [
    HousingCenterComponent,
    HousingListComponent,
    HousingDetailsComponent,
    HousingFormComponent
  ],
  imports: [
    CommonModule,
    HousingsRoutingModule,
    FormsModule
  ]
})
export class HousingsModule { }
