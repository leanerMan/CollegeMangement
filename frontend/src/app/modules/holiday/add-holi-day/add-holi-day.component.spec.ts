import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHoliDayComponent } from './add-holi-day.component';

describe('AddHoliDayComponent', () => {
  let component: AddHoliDayComponent;
  let fixture: ComponentFixture<AddHoliDayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddHoliDayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddHoliDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
