import { Component } from '@angular/core';
import { HolidayService } from '../../../core/service/holiday.service';
import { Holiday } from '../../../models/holiday';
import {PageEvent,MatPaginatorModule} from '@angular/material/paginator';
import { Router } from '@angular/router';

import swal from 'sweetalert';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-holi-day',
  standalone: true,
  imports: [MatPaginatorModule,FormsModule],
  templateUrl: './all-holi-day.component.html',
  styleUrl: './all-holi-day.component.css'
})
export class AllHoliDayComponent {

  constructor(private holidayservice:HolidayService,private router:Router){}

  navigateHoliday(){
    this.router.navigate(['admin','holi-day','add'])
  }


  ngOnInit(){
    this.retrieveHoliday(this.pageIndex, this.pageSize,this.keyword);
  }

  currentTimeTable: any;
  currentIndex = -1;
  keyword ='';

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
    this.retrieveHoliday(this.pageIndex, this.pageSize,this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  pageable:any;
  holiday:Holiday[]=[];
  retrieveHoliday(pageIndex:number, pageSize:number,keyword:string){
    this.holidayservice.getAllHoliday(pageIndex,pageSize,keyword).subscribe({
      next: (res)=>{
        console.log(res.data)
        this.holiday=res.data.content;
        console.log(res.data.content);
        this.length=res.data.totalElements;    
      },error : (e)=>console.error(e)
    })
  }



  editHoliday(id:string){
    this.holidayservice.setSelectedItemId(id);
    this.router.navigate(['admin','holi-day','edit']);
  }


  deleteHoliday(holiday: Holiday): void {
    swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover!",
      icon: "warning",
      buttons: ['cancel','yes'],
      dangerMode:true
    })
    .then((willDelete) => {
      if (willDelete) {
        if(holiday.id)
        this.holidayservice.removeHoliday(holiday.id)
          .subscribe({
            next: (res) => {
              console.log(res);
              this.retrieveHoliday(this.pageIndex, this.pageSize,this.keyword);
            },
            error: (e) => console.error(e)
          });
        swal("Holiday has been deleted!", {
          icon: "success",
        });
      } 
    });

  }

  search(): void {
    this.pageIndex = 0;
    this.retrieveHoliday(this.pageIndex, this.pageSize,this.keyword);
  }


}
