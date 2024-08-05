import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../../../core/service/course.service';
import { FormsModule } from '@angular/forms';
import { Course } from '../../../models/course.model';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';

@Component({
  selector: 'app-edit-course',
  standalone: true,
  imports: [FormsModule, MatDatepickerModule, MatDatepickerModule],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
  templateUrl: './edit-course.component.html',
  styleUrl: './edit-course.component.css'
})
export class EditCourseComponent implements OnInit {
cancel() {
  this.router.navigate(['admin', 'courses', 'all']);
}

  selectedId: string | null | undefined
  selectedCourse: Course | undefined;
  submitted = false;


  constructor(private router: Router, private courseService: CourseService) { }

  ngOnInit() {
    this.selectedId = this.courseService.getSelectedItemId();
    if (this.selectedId == null || this.selectedId == undefined) {
      this.router.navigate(['admin', 'courses', 'all'])
    } else {
      this.courseService.getCoursesById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedCourse = res.data;
        },
        error: (e) => console.error(e)
      });


    }
  }

  onDateReturn(event: any): any {
    return event.format('YYYY-MM-DD');
  }


  updateCourse() {

    // Implement the save logic here using your service
    if (this.selectedCourse != null)
    this.selectedCourse.startDate= (typeof this.selectedCourse.startDate === 'string') ?  this.selectedCourse.startDate : this.onDateReturn(this.selectedCourse.startDate),
      this.courseService.updateCourse(this.selectedCourse).subscribe({
        next: (res) => {
          console.log(res);
          alert('Record Updated Successfully!!');
          this.router.navigate(['admin', 'courses', 'all']);
        },
        error: (e) => {
          alert('An error occurred while Updating the record!');
          console.error(e);
        }
      });
  }
}
