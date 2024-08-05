import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLibraryAssetComponent } from './edit-library-asset.component';

describe('EditLibraryAssetComponent', () => {
  let component: EditLibraryAssetComponent;
  let fixture: ComponentFixture<EditLibraryAssetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditLibraryAssetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditLibraryAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
