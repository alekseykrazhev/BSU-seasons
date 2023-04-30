import { TestBed } from '@angular/core/testing';

import { HousingsService } from './housings.service';

describe('HousingsService', () => {
  let service: HousingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HousingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
