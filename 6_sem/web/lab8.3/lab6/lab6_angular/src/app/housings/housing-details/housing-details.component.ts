import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Housing } from '../housings.model';
import { OnInit } from '@angular/core';



@Component({
  selector: 'app-housing-details',
  templateUrl: './housing-details.component.html',
  styleUrls: ['./housing-details.component.css']
})
export class HousingDetailsComponent implements OnInit {
  @Input() housing: Housing | undefined;
  @Input() description: string | undefined;
  @Input() worker: string | undefined;

  constructor(public router: Router){
  }

  ngOnInit() {
    
  }

  onSubmit() {
    console.log(`Description: ${this.description}`);
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
