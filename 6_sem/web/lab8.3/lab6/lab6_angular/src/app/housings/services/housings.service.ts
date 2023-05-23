import { Injectable } from '@angular/core';
import { Housings } from '../mock-housing-list';
import { Observable, of } from 'rxjs';
import { Housing } from '../housings.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class HousingsService {

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

  private apiUrl = 'http://localhost:8080/api/applications';

  constructor(private http: HttpClient) {}

  getAllHousings(): Observable<Housing[]> {
    return this.http.get<Housing[]>(this.apiUrl);
  }

  getHousingById(id: number): Observable<Housing> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Housing>(url);
  }

  addHousing(housing: Housing): Observable<Housing> {
    return this.http.post<Housing>(this.apiUrl, housing);
  }

  deleteHousing(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
