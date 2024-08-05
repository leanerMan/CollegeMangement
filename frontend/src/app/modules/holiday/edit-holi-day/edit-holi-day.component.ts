import { Component } from '@angular/core';
import { HolidayService } from '../../../core/service/holiday.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import swal from 'sweetalert';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-holi-day',
  standalone: true,
  imports: [FormsModule,CommonModule,MatDatepickerModule,ReactiveFormsModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './edit-holi-day.component.html',
  styleUrl: './edit-holi-day.component.css'
})
export class EditHoliDayComponent {

  constructor(private holidayService:HolidayService,private router:Router){}

  holidayId:any;
  selectHoliday:any;

  ngOnInit(){
    this.holidayId=this.holidayService.getSelectedItemId();
    if(this.holidayId){
      this.holidayService.getHolidayById(this.holidayId).subscribe({
        next:(res)=>{
          this.selectHoliday = res.data
        },error:(e)=>{
          console.log("Some thing is fissy");
          swal('Oops...', 'Something went wrong!', 'error');
        }
      })

    }

  }

 updateHoliday(){
  if(this.selectHoliday){
    console.log("select",this.selectHoliday);
    this.holidayService.addHoliday(this.selectHoliday).subscribe({
      next:(res)=>{
        swal('Successfully', 'Your Data updated!', 'success');
      },error:(e)=>{
        console.log("Some thing is fissy");
        swal('Oops...', 'Something went wrong!', 'error');
      }
    })
  }
 };


 naviReturnHoliday(){
  this.router.navigate(['admin','holi-day','all'])
 }

}
