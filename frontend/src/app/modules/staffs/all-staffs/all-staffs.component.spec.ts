import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllStaffsComponent } from './all-staffs.component';

describe('AllStaffsComponent', () => {
  let component: AllStaffsComponent;
  let fixture: ComponentFixture<AllStaffsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllStaffsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllStaffsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
