import { Component, OnInit, inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideMomentDateAdapter } from '@angular/material-moment-adapter';
import { MatDatepicker, MatDatepickerModule } from '@angular/material/datepicker';

import moment, { Moment } from 'moment';

import { StudentService } from '../../../core/service/student.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'YYYY-MM',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};
@Component({
  selector: 'app-student-attendance',
  standalone: true,
  providers: [
    // Moment can be provided globally to your app by adding `provideMomentDateAdapter`
    // to your app config. We provide it at the component level here, due to limitations
    // of our example generation script.
    provideMomentDateAdapter(MY_FORMATS),
  ],
  imports: [
    MatDatepickerModule,
    FormsModule,
    ReactiveFormsModule, MatPaginatorModule],
  templateUrl: './student-attendance.component.html',
  styleUrl: './student-attendance.component.css'
})
export class StudentAttendanceComponent implements OnInit {
  daysCount = 30;
  constructor(private studentService: StudentService) {

  }
  ngOnInit(): void {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0'); // Adding 1 because months are zero-based
    this.selectedDate = `${year}-${month}`;
    this.getAttendanceList(this.pageIndex, this.pageSize, this.selectedDate);

  }
  selectedDate = "";

  attendanceList: any;

  counter(i: number) {
    return new Array(i);
  }


  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const formattedDate = normalizedMonthAndYear.format('yyyy-MM');
    this.selectedDate = formattedDate;
    this.daysCount = normalizedMonthAndYear.daysInMonth();
    datepicker.close();
    this.getAttendanceList(this.pageIndex, this.pageSize, this.selectedDate);
  }

  getAttendanceList(pageIndex: number, pageSize: number, selectedDate: string) {

    this.studentService.getStudentAttendance(this.pageIndex, this.pageSize, this.selectedDate).subscribe({
      next: (res) => {
        this.length = res.data.totalElements;
        this.attendanceList = res.data.content;
      },
      error: (e) => console.error(e)
    })

  }

  //pagination
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
    this.getAttendanceList(this.pageIndex, this.pageSize, this.selectedDate);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }



}
