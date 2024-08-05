import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllLibraryAssetComponent } from './all-library-asset.component';

describe('AllLibraryAssetComponent', () => {
  let component: AllLibraryAssetComponent;
  let fixture: ComponentFixture<AllLibraryAssetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllLibraryAssetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllLibraryAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
