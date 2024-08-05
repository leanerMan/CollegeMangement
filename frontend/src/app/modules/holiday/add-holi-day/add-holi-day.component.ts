import { Component } from '@angular/core';
import { Holiday } from '../../../models/holiday';
import { HolidayService } from '../../../core/service/holiday.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import swal from 'sweetalert';
import { Router } from '@angular/router';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-add-holi-day',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,CommonModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-holi-day.component.html',
  styleUrl: './add-holi-day.component.css'
})
export class AddHoliDayComponent {

  holiday:Holiday={
    id:'',
    title:'',
    startDate:'',
    endDate:'',
    holidayType:'',
    holidayDetails:''
  }

  constructor(private holidayservice:HolidayService,private router:Router){}

 navigateList(){
  this.router.navigate(['admin','holi-day','all']);
 }

  ngOnInit(){
  }

  onDateReturn(event: any): any {
    return event.format('YYYY-MM-DD');
  }


  saveHoliday() :void{
    const data: Holiday ={
      id:this.holiday.id,
      title:this.holiday.title,
      startDate:this.onDateReturn(this.holiday.startDate),
      endDate:this.onDateReturn(this.holiday.endDate),
      holidayType:this.holiday.holidayType,
      holidayDetails:this.holiday.holidayDetails

    };
    console.log(data);
    this.holidayservice.addHoliday(data).subscribe({
      next : (res)=>{
        console.log(res.data);
        swal('Successfully', 'Your Data Saved!', 'success');
        this.navigateList();
      },error :(e)=>{
        console.log(e);
        swal('Oops...', 'Something went wrong!', 'error');
      }
    })
  }


}
