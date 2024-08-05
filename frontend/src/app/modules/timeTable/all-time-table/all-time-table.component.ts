import { Component, ViewChild } from '@angular/core';
import { TimeTableService } from '../../../core/service/time-table.service';
import { Router } from '@angular/router';
import { TimeTable } from '../../../models/time-table';
import { DatePipe } from '@angular/common';
import {PageEvent,MatPaginatorModule} from '@angular/material/paginator';

import swal from 'sweetalert';
import { FormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';




@Component({
  selector: 'app-all-time-table',
  standalone: true,
  imports: [MatPaginatorModule,FormsModule,DatePipe,MatTooltipModule],
  templateUrl: './all-time-table.component.html',
  styleUrl: './all-time-table.component.css'
})
export class AllTimeTableComponent{

    constructor(private timeTableService:TimeTableService,private router:Router) {}

  ngOnInit():void{
    this.retrieveTimeTable(this.pageIndex,this.pageSize,this.keyword);
  }

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
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.retrieveTimeTable(this.pageIndex, this.pageSize,this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }


 
  pageable:any;
  timeTable:TimeTable[]=[];
  retrieveTimeTable(pageIndex:number, pageSize:number, keyword:string){
    this.timeTableService.getTimeTable(pageIndex, pageSize, keyword).subscribe({
      next: (res)=>{
        console.log(res.data)
        this.timeTable=res.data.content;
        this.length=res.data.totalElements;    
      },error : (e)=>console.error(e)
    })
    
  }




  deleteTimeTable(timeTable: TimeTable): void {
    swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover!",
      icon: "warning",
      buttons: ['cancel','yes'],
      dangerMode:true
    })
    .then((willDelete) => {
      if (willDelete) {
        if(timeTable.id)
        this.timeTableService.removeTimeTable(timeTable.id)
          .subscribe({
            next: (res) => {
              console.log(res);
              this.retrieveTimeTable(this.pageIndex,this.pageSize,this.keyword);
            },
            error: (e) => console.error(e)
          });
        swal("Holiday has been deleted!", {
          icon: "success",
        });
      } 
    });

  }

  editTimetable(id:string){
    this.timeTableService.setSelectedItemId(id);
    this.router.navigate(['admin','time-tables','edit']);
  }

  navigateAdd(){
    this.router.navigate(['admin','time-tables','add'])
  }


  // search(): void {
  //   const trimmedkeyword = this.keyword.trim();
  //   if(trimmedkeyword!== ''){
  //     this.timeTableService.getTimeTableById(trimmedkeyword)
  //     .subscribe({
  //       next: (res) => {
  //         console.log("search data",res.data);
  //         this.timeTable = res.data;
  //       },
  //       error: (e) => console.error(e.error.message)
  //     });
  //   }
   
  // }

  search(): void {
    this.pageIndex = 0;
    this.retrieveTimeTable(this.pageIndex, this.pageSize,this.keyword);
  }



}
