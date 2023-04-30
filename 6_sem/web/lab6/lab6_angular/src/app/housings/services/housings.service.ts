import { Injectable } from '@angular/core';
import { Housings } from '../mock-housing-list';
import { Observable, of } from 'rxjs';
import { Housing } from '../housings.model';


@Injectable({
  providedIn: 'root'
})
export class HousingsService {

  constructor() { }

  getHousings(): Observable<Housing[]> {
    return of(Housings);
  }

  getHousing(id: number): Housing | undefined {
    return Housings.find(housing => housing.id === id);
  }

  updateHousing(housing: Housing): Observable<Housing | undefined> {
    const index = Housings.findIndex(h => h.id === housing.id);
    if (index === -1) {
      return of(undefined);
    }
    Housings[index] = housing;
    return of(housing);
  }
}
