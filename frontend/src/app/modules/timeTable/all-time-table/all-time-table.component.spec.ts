import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTimeTableComponent } from './all-time-table.component';

describe('AllTimeTableComponent', () => {
  let component: AllTimeTableComponent;
  let fixture: ComponentFixture<AllTimeTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllTimeTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllTimeTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
