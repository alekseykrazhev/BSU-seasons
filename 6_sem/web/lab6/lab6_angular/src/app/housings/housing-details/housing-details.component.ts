import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Housings } from '../mock-housing-list';
import { Housing } from '../housings.model';
import { HousingsService } from '../services/housings.service';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';



@Component({
  selector: 'app-housing-details',
  templateUrl: './housing-details.component.html',
  styleUrls: ['./housing-details.component.css']
})
export class HousingDetailsComponent implements OnInit {
  housing: Housing | undefined;

  constructor(private route: ActivatedRoute, private housingService: HousingsService, public router: Router){
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.housing = this.housingService.getHousing(id);
     });
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
