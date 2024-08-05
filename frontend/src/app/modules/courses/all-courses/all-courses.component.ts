import { Component } from '@angular/core';
import { CourseService } from '../../../core/service/course.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { Course } from '../../../models/course.model';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { DatePipe } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-courses',
  standalone: true,
  imports: [MatPaginatorModule, MatTooltipModule, DatePipe,RouterLink,FormsModule],
  templateUrl: './all-courses.component.html',
  styleUrl: './all-courses.component.css'
})
export class AllCoursesComponent {
  constructor(private router: Router,private courseService: CourseService) {}

  editCourse(course: Course) {
    this.courseService.setSelectedItemId(course.id+'');
    this.router.navigate(['admin','courses', 'edit']);
  }

  viewCourse(course: Course) {
    this.courseService.setSelectedItemId(course.id+'');
    this.router.navigate(['admin','courses', 'about']);
  }
  
    ngOnInit(): void { 
      this.retrieveCourses(this.pageIndex, this.pageSize,this.keyword);
    }

    courses: Course[]=[];
   
    currentIndex = -1;
    keyword = "";
  
    retrieveCourses(pageIndex :number, pageSize:number,keyword:string): void {
      this.courseService.getCourses(pageIndex,pageSize,keyword)
        .subscribe({
          next: (data) => {
            this.courses = data.data.content;
            this.length = data.data.totalElements;
            console.log(data);
          },
          error: (e) => console.error(e)
        });
    }
  
    refreshList(): void {
      this.retrieveCourses(this.pageIndex, this.pageSize,this.keyword);
      this.currentIndex = -1;
    }
  
    setActiveCourse(coures: Course, index: number): void {
      this.currentIndex = index;
    }
    
    removeCourse(id: number | null) {
      if (id !== null) {
        this.courseService.deleteCourse(id).subscribe({
          next: () => {
            this.courses = this.courses.filter(item => item.id !== id);
            console.log('Course deleted successfully!');
          },
          error: (error: HttpErrorResponse) => {
            console.error('Error deleting course:', error);
          }
        });
      } else {
        alert("Id must not be null");
      }
    }

      // currentPage: number = 0;
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
    this.retrieveCourses(this.pageIndex, this.pageSize,this.keyword);
  }

  search(): void {
    console.log("data search",this.keyword)
    this.pageIndex = 0;
    this.retrieveCourses(this.pageIndex, this.pageSize,this.keyword);

  }
    

}
