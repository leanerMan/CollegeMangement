import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLibraryAssetComponent } from './add-library-asset.component';

describe('AddLibraryAssetComponent', () => {
  let component: AddLibraryAssetComponent;
  let fixture: ComponentFixture<AddLibraryAssetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddLibraryAssetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddLibraryAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
