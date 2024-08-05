import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../../../core/service/course.service';
import { Course } from '../../../models/course.model';

@Component({
  selector: 'app-about-course',
  standalone: true,
  imports: [],
  templateUrl: './about-course.component.html',
  styleUrl: './about-course.component.css'
})
export class AboutCourseComponent implements OnInit{

  selectedId?:string | null | undefined 
  selectedCourse: any;
  constructor(private route: Router, private courseService: CourseService){}


  ngOnInit() {
    this.selectedId = this.courseService.getSelectedItemId();
    if (this.selectedId==null || this.selectedId==undefined) {
      this.route.navigate(['admin','courses','all'])
    } else {
      this.courseService.getCoursesById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedCourse = res.data
          // this.length = res.data.totalElements;

        },
        error: (e) => console.error(e)
      });
    }
  

  }

}
