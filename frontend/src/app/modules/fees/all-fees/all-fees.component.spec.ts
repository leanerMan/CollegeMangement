import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllFeesComponent } from './all-fees.component';

describe('AllFeesComponent', () => {
  let component: AllFeesComponent;
  let fixture: ComponentFixture<AllFeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllFeesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllFeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
