import { Component } from '@angular/core';

import { HousingDetailsComponent } from '../housing-details/housing-details.component';
import { HousingsModule } from '../housings.module';
import { Housings } from '../mock-housing-list';
import { Housing } from '../housings.model';
import { HousingsService } from '../services/housings.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-housing-list',
  templateUrl: './housing-list.component.html',
  styleUrls: ['./housing-list.component.css']
})

export class HousingListComponent {
  housings: Housing[] = [];
    selectedHousing: Housing | undefined;

  constructor(private housingService: HousingsService, public router: Router) {
    this.housingService.getHousings().subscribe((housings: Housing[]) => this.housings = housings);
  }

  ngOnInit() {
    this.getAllHousings();
  }

  getAllHousings(): void {
    this.housingService.getAllHousings().subscribe(
      (housings: Housing[]) => {
        this.housings = housings;
        console.log(this.housings);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  onSelect(housing: Housing): void {
    const id = housing.id;
    console.log(id);
    this.selectedHousing = housing;
  }
}
