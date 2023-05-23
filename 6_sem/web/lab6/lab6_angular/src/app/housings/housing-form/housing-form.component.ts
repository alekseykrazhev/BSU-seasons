import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { Housings } from '../mock-housing-list';


@Component({
  selector: 'app-housing-form',
  templateUrl: './housing-form.component.html',
  styleUrls: ['./housing-form.component.css']
})
export class HousingFormComponent {
  @Input() description: string | undefined;
  @Input() worker: string | undefined;

  onSubmit(contactForm: { value: any; }) {
    console.log(contactForm.value);
    const lastIndex = Housings.at(-1)?.id;
    Housings.push({
      id: lastIndex! + 1,
      description: contactForm.value.description,
      worker: contactForm.value.worker
    })
  }
}
