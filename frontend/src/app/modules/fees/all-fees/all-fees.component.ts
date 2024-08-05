import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { feeService } from '../../../core/service/fee.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { Fee } from '../../../models/fee';
import swal from 'sweetalert';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-fees',
  standalone: true,
  imports: [MatPaginatorModule,FormsModule],
  templateUrl: './all-fees.component.html',
  styleUrl: './all-fees.component.css'
})
export class AllFeesComponent implements OnInit {

  searchTerm: string = ''; // Add the searchTerm property
  constructor(private router: Router,private feeservice: feeService) {}

  currentTimeTable: any;
  currentIndex = -1;
  keyword ="";
  

  pageSize: number = 5;
  length:number=0; 

  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;
  pageEvent: PageEvent | undefined;
  handlePageEvent(e: PageEvent) {
    console.log(e,"><>")
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.fetchFeeList(this.pageIndex, this.pageSize,this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  navigateToAddFee() {
    this.router.navigate(['/home/fees/add'])
    }


    editfee(id:number){
      console.log(id);
      this.router.navigate(['/home/fees/edit/',id])
    }

  ngOnInit(): void {
   this.fetchFeeList(this.pageIndex,this.pageSize,this.keyword);
  }

  fee:Fee[]=[];
  fetchFeeList(pageIndex:number, pageSize:number, keyword:string) {
    this.feeservice.getFeeList(pageIndex, pageSize, keyword)
          .subscribe({
            next: (res) => {
              console.log(res);
             this.fee=res.data.content;
             console.log(this.fee)
              this.length=res.data.totalElements;
             
             
            },
            error: (e) => console.error(e)
          });
   
    
   }
  
   deletefee(id: number){
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
        this.feeservice.deletefee(id)
          .subscribe({
            next: (res) => {
              console.log(res);
              swal(res.data, {
                icon: "success",
              });
              // Perform any additional actions after deletion, such as refreshing the staff list
              this.fetchFeeList(this.pageIndex,this.pageSize,this.keyword);
            },
            error: (e) => console.error(e)
          });
      } else {
        // If user cancels, do nothing
        console.log('Deletion canceled');
      }
    });
   

   }

   search(){
    this.pageIndex = 0;
    this.fetchFeeList(this.pageIndex, this.pageSize,this.keyword);
  }

}
