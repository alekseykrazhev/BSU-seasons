import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HousingCenterComponent } from './housing-center.component';

describe('HousingCenterComponent', () => {
  let component: HousingCenterComponent;
  let fixture: ComponentFixture<HousingCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HousingCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HousingCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
