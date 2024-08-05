import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Library } from '../../../models/library';
import { LibraryService } from '../../../core/service/library.service';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-edit-library-asset',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-library-asset.component.html',
  styleUrl: './edit-library-asset.component.css'
})
export class EditLibraryAssetComponent implements OnInit {

  cancel() {
    // throw new Error('Method not implemented.');
    }
    
    libraryId?: string='';
    selectedLibrary: Library={
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
      };
     
    
      constructor(private route: ActivatedRoute, private libraryService: LibraryService,private router:Router) {}
    
      ngOnInit() {
        // this.selectedLibrary=this.sharedService.getSelectedItem();
        // this.route.paramMap.subscribe((params) => {
        //     this.libraryId = params.get('id')  || '';
        //   // Fetch teacher data based on the ID
        //   if(this.libraryId)
        //     this.libraryService.getLibraryById(this.libraryId).subscribe((res) => {
        //       this.selectedLibrary = res.data;
        //       console.log(this.selectedLibrary);
        //     });
    
        // });
      }
    
      savelibrary() {
     
        // Implement the save logic here using your service
      if(this.selectedLibrary && this.libraryId)
        this.libraryService.editLibrary(this.libraryId,this.selectedLibrary).subscribe({
          next :(res) => {
            alert(res.data.message);
            this.router.navigate(['admin', 'library', 'all']);
            },
            error :(err)=>{
              alert(err.error.message);
            }
      });
      }

}


