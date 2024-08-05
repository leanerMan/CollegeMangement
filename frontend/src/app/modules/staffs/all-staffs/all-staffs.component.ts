
import { Component, OnInit } from '@angular/core';

import { StaffService } from '../../../core/service/staff.service';
import { Router } from '@angular/router';
import { Staff } from '../../../models/staff';
import swal from 'sweetalert';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-staffs',
  standalone: true,
  imports: [MatPaginatorModule, FormsModule],
  templateUrl: './all-staffs.component.html',
  styleUrl: './all-staffs.component.css'
})
export class AllStaffsComponent implements OnInit {
  searchTerm: string = ''; // Add the searchTerm property
  constructor(private router: Router, private staffService: StaffService) { }


  currentTimeTable: any;
  currentIndex = -1;
  keyword = "";


  pageSize: number = 5;
  length: number = 0;

  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;
  pageEvent: PageEvent | undefined;
  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.fetchStaffList(this.pageIndex, this.pageSize, this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  navigateToAddStaff() {
    this.router.navigate(['admin','staffs','add'])
  }
  editstaff(id: string) {
    console.log(id);
    // this.router.navigate(['/home/staffs/edit/', id])
    this.router.navigate(['admin','staffs','edit'])
  }

  // search(event: Event) {
  //   const searchTerm = (event.target as HTMLInputElement).value;
  //   console.log(searchTerm);
  //   // Check if searchTerm is not null or undefined
  //   if (searchTerm) {
  //     // Do something with the searchTerm, such as filtering data or making an API call
  //     console.log(searchTerm);
  //     this.staffService.getStaffList(pageIndex:number, pageSize:number, keyword:string)
  //       .subscribe({
  //         next: (res) => {
  //           console.log(res.data, "search result");
  //           this.staff = res.data;
  //         },
  //         error: (e) => console.error(e)
  //       });
  //   } else {
  //     // Handle case when searchTerm is null or undefined
  //     console.log("Search term is empty.");
  //     // Optionally, you can clear the search results or display a message to the user
  //     this.staff =  []; // Clear the staff data
  //   }
  // }

  // deletetstaff(id:number){
  //   this.staffService.deletestaff(id)
  //   .subscribe({
  //     next: (res) => {
  //       console.log(res);
  //       this.fetchStaffList();

  //     },
  //     error: (e) => console.error(e)
  //   });
  // }

  search() {
    this.pageIndex = 0;
    this.fetchStaffList(this.pageIndex, this.pageSize, this.keyword);
  }

  deletestaff(id: string) {
    // Show a confirmation dialog using SweetAlert
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this staff record!',
      icon: 'warning',
      buttons: ['Cancel', 'Delete'],
      dangerMode: true,
    })
      .then((willDelete: boolean) => {
        if (willDelete) {
          // If user confirms, proceed with deletion
          this.staffService.deletestaff(id)
            .subscribe({
              next: (res) => {
                console.log(res);
                swal("Poof! Your record has been deleted!", {
                  icon: "success",
                });
                // Perform any additional actions after deletion, such as refreshing the staff list
                this.fetchStaffList(this.pageIndex, this.pageSize, this.keyword);
              },
              error: (e) => console.error(e)
            });
        } else {
          // If user cancels, do nothing
          console.log('Deletion canceled');
        }
      });
  }


  ngOnInit(): void {
    this.fetchStaffList(this.pageIndex, this.pageSize, this.keyword);
  }

  staff: Staff[] = [];


  fetchStaffList(pageIndex: number, pageSize: number, keyword: string) {
    this.staffService.getStaffList(pageIndex, pageSize, keyword)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.staff = res.data.content;
          this.length = res.data.totalElements;
          console.log(this.staff, "??????/");

        },
        error: (e) => console.error(e)
      });


  }

}
