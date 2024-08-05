import { Component } from '@angular/core';
import { LibraryService } from '../../../core/service/library.service';
import { Library } from '../../../models/library';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-library-asset',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-library-asset.component.html',
  styleUrl: './add-library-asset.component.css'
})
export class AddLibraryAssetComponent {

  library: Library={
    id:0,
    billNo: '',
    title: '',
    subject: '',
    publisher: '',
    department: '',
    assetType: '',
    purchaseDate: '',
    price: 0,
    status: '',
    details: '',
  }
  
  constructor( private libraryService: LibraryService, private router:Router) {}
  ngOnInit(): void {
    
  }
  submitted = false;

  savelibrary(): void {
    const data : Library= {
      id:this.library.id,
      billNo:this.library.billNo,
      title: this.library.title,
      subject: this.library.subject,
      publisher: this.library.publisher,
      department: this.library.department,
      assetType: this.library.assetType,
      purchaseDate: this.library.purchaseDate,
      price: this.library.price,
      status: this.library.status,
      details: this.library.details
    
    };
    console.log(data);
        this.libraryService.addLibrary(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          this.router.navigate(['admin','library', 'all']);
        },
        error: (e) => console.error(e)
      });
  }
    newLibrary():void {
      this.submitted = false;
      this.library = {
        id:0,
        billNo: '',
        title: '',
        subject: '',
        publisher: '',
        department: '',
        assetType: '',
        purchaseDate: '',
        price: 0,
        status: '',
        details: ''
  
      }
    }
    cancel(): void {
      // Perform your cancel logic here
    }
  

}