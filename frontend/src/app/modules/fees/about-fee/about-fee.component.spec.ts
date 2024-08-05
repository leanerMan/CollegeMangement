import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutFeeComponent } from './about-fee.component';

describe('AboutFeeComponent', () => {
  let component: AboutFeeComponent;
  let fixture: ComponentFixture<AboutFeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AboutFeeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AboutFeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
