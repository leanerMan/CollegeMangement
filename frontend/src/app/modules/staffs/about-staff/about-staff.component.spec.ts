import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutStaffComponent } from './about-staff.component';

describe('AboutStaffComponent', () => {
  let component: AboutStaffComponent;
  let fixture: ComponentFixture<AboutStaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AboutStaffComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AboutStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
