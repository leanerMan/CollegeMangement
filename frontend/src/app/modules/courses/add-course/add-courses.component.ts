import { Component } from '@angular/core';

import { CourseService } from '../../../core/service/course.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Course } from '../../../models/course.model';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';

@Component({
  selector: 'app-add-courses',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatDatepickerModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-courses.component.html',
  styleUrl: './add-courses.component.css'
})
export class AddCoursesComponent {
  course: Course={
    id: null,
    courseName: "",
    courseCode: "",
    courseTimeLength: "",
    startDate: "",
    professorName: "",
    contactNumber: "",
    courseDetails: ""
  };

  constructor(private router: Router, private courseService: CourseService) {}

  ngOnInit(): void {

  }

  submitted = false;
  file?: File ;
 

  

  saveCourse(): void {
    const data : Course= {
      id: this.course.id,
      courseName: this.course.courseName,
      courseCode: this.course.courseCode,
      courseTimeLength: this.course.courseTimeLength,
      startDate: this.course.startDate.toISOString().slice(0, 10),
      professorName:this.course.professorName,
      contactNumber: this.course.contactNumber,
      courseDetails: this.course.courseDetails
    };
    console.log(data);

    const formData = new FormData();
    formData.append('data', JSON.stringify(data)); // Convert data to JSON string
    console.log(formData.get('data')); // Retrieve a specific value by key

    this.courseService.addCourse(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          alert('Record Saved Successfully!!');
          this.router.navigate(['admin','courses', 'all']);
        },
        error: (e) => {
          alert('An error occurred while saving the record!');
          console.error(e);
        }
      });
  }

  newCourse(): void {
    this.submitted = false;
    this.course = {
      id: 0,
    courseName: "",
    courseCode: "",
    courseTimeLength: "",
    startDate: "",
    professorName: "",
    contactNumber: "",
    courseDetails: ""
    };
  }

  cancel(): void {
    
  }
}