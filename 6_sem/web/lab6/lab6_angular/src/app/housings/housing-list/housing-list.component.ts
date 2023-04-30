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
  housings: Housing[] = Housings;

  constructor(private housingService: HousingsService, public router: Router) {
    this.housingService.getHousings().subscribe((housings: Housing[]) => this.housings = housings);
  }

  onSelect(housing: Housing): void {
    const id = housing.id;
    console.log(id);
    this.router.navigate(['/', id]);
  }
}
