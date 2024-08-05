import { Component } from '@angular/core';
import { TimeTableService } from '../../../core/service/time-table.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import swal from 'sweetalert';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';

@Component({
  selector: 'app-edit-time-table',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatNativeDateModule,ReactiveFormsModule,CommonModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './edit-time-table.component.html',
  styleUrl: './edit-time-table.component.css'
})
export class EditTimeTableComponent {

  constructor(private timeTableService:TimeTableService,private router:Router){}

  timeTableId:any;
  selectTimeTable:any;

  ngOnInit(){
    this.timeTableId =this.timeTableService.getSelectedItemId();
    if(this.timeTableId){
      this.timeTableService.getTimeTableById(this.timeTableId).subscribe({
        next:(res)=>{
          this.selectTimeTable = res.data
        },error:(e)=>{
          console.log("Some thing is fissy");
          swal('Oops...', 'Something went wrong!', 'error');
        }
      })

    }
  }

    saveTimeTable() {
      if(this.selectTimeTable){
        console.log("Upadte",this.selectTimeTable)
        this.timeTableService.addTimeTable(this.selectTimeTable).subscribe({
          next:(res)=>{
           
             swal('Hi user', 'Your Data update!', 'success');
             this.naviGatePage();
          },error:(e)=>{
            swal('Oops...', 'Something went wrong!', 'error');
          }

        })

      }
    }

    naviGatePage(){
      this.router.navigate(['admin','time-tables','all'])
    }
    navigateAll(){
      this.router.navigate(['admin','time-tables','all'])
    }
  
    
 
}
