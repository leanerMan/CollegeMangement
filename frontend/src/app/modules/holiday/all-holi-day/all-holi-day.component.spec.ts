import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllHoliDayComponent } from './all-holi-day.component';

describe('AllHoliDayComponent', () => {
  let component: AllHoliDayComponent;
  let fixture: ComponentFixture<AllHoliDayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllHoliDayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllHoliDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
