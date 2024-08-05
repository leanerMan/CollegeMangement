import { FormsModule } from '@angular/forms';
import { TimeTable } from '../../../models/time-table';
import { TimeTableService } from '../../../core/service/time-table.service';
import { Router } from '@angular/router';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import swal from 'sweetalert';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';
import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-add-time-table',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatNativeDateModule,ReactiveFormsModule,CommonModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-time-table.component.html',
  styleUrl: './add-time-table.component.css'
})
export class AddTimeTableComponent {



  timetable: TimeTable={
    id:'',
    teacher:'',
    subject:'',
    semester:'',
    roomNo:'',
    date:'',
    startTime:'',
    endTime:''
  };

  constructor(private timeTableServiec:TimeTableService,private router:Router){}

  ngOnInit(){

  }
  editTimetable(timeTable:TimeTable){
    console.log("edit data",timeTable);
    this.router.navigate(['admin','time-tables','edit',timeTable.id]);

  }

  numberInput: string = ''; // variable to store the number input

  onDateReturn(event: any): any {
    // Convert Moment object to JavaScript Date object
    return event.format('YYYY-MM-DD');
  }

  saveTimeTable() : void {
      const data : TimeTable={
        id:this.timetable.id,
        teacher: this.timetable.teacher,
        subject: this.timetable.subject,
        semester: this.timetable.semester,
        roomNo: this.timetable.roomNo,
        date:  this.onDateReturn(this.timetable.date),
        startTime: this.timetable.startTime,
        endTime: this.timetable.endTime
      };

      console.log(data);
      this.timeTableServiec.addTimeTable(data).subscribe({
        next:(res)=>{
          console.log("save data",res.data);
          swal('Successfully', 'Your Data Saved!', 'success');
          this.naviGatePage();
        },error:(e)=>{
          console.log(e);
          swal('Oops...', 'Something went wrong!', 'error');

        }
      })
  }

  naviGatePage(){
    this.router.navigate(['admin','time-tables','all'])
  }

  ResetForm(){
    this.timetable.roomNo='';
  }



}
