import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHoliDayComponent } from './edit-holi-day.component';

describe('EditHoliDayComponent', () => {
  let component: EditHoliDayComponent;
  let fixture: ComponentFixture<EditHoliDayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditHoliDayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditHoliDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
