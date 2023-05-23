import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { Housings } from '../mock-housing-list';
import { HousingsService } from '../services/housings.service';
import { Housing } from '../housings.model';


@Component({
  selector: 'app-housing-form',
  templateUrl: './housing-form.component.html',
  styleUrls: ['./housing-form.component.css']
})
export class HousingFormComponent {
  @Input() description: string | undefined;
  @Input() worker: string | undefined;

  constructor(private housingService: HousingsService) {
  }

  onSubmit(contactForm: { value: any; }) {
    console.log(contactForm.value);
    const lastIndex = Housings.at(-1)?.id;
    const housing: Housing = {
      id: 0,
      name: contactForm.value.description,
      id_type: contactForm.value.worker,
      tenant_id: 2,
      date: "2023-01-01",
      dispatcher_id: 1,
    }
    console.log(housing.tenant_id);
    console.log(housing);
    this.housingService.addHousing(housing).subscribe(
      (housing: Housing) => {
        console.log('Project added:', housing);
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
