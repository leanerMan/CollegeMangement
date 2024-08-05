import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../../../core/service/library.service';
import { Library } from '../../../models/library';
import { Router } from '@angular/router';


@Component({
  selector: 'app-all-library-asset',
  standalone: true,
  imports: [],
  templateUrl: './all-library-asset.component.html',
  styleUrl: './all-library-asset.component.css'
})
export class AllLibraryAssetComponent implements OnInit{

aboutlibrary(library: Library) {
  // this.sharedService.setSelectedItem(library);
  this.router.navigate(['admin', 'library', 'about']);
}

  constructor(private libraryService: LibraryService,private router :Router){  }
  libraries:any;
  ngOnInit(): void {
    this.libraryService.getLibrary().subscribe({
      next :(res)=>{
        this.libraries=res.data
      },
      error:(err)=>{
    console.log(err.message);
      }
    });
  }
  editlibrary(library: Library) {
    // this.sharedService.setSelectedItem(library);
    this.router.navigate(['admin', 'library', 'edit']);
  }
  deletelibrary(id:number){
    this.libraryService.deleteLibrary(id).subscribe({
      next :(res)=>{
        alert('deleted')
      },
      error:(err)=>{
    console.log(err.message);
      }
    })
  }
}
