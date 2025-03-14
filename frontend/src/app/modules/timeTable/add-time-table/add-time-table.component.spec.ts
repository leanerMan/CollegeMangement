import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTimeTableComponent } from './add-time-table.component';

describe('AddTimeTableComponent', () => {
  let component: AddTimeTableComponent;
  let fixture: ComponentFixture<AddTimeTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddTimeTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddTimeTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
