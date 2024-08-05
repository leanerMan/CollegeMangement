import { Component } from '@angular/core';
import { Router,RouterLink} from '@angular/router';

import { Student } from '../../../models/student';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';
import { StudentService } from '../../../core/service/student.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-student',
  standalone: true,
  imports: [RouterLink,MatPaginatorModule, MatTooltipModule, DatePipe,FormsModule],
  templateUrl: './all-student.component.html',
  styleUrl: './all-student.component.css'
})
export class AllStudentComponent {
newStudent() {
  this.router.navigate(['admin', 'students', 'add']);
}

  constructor(private router: Router,private studentService: StudentService) {}

  editStudent(id: string) {
    this.studentService.setSelectedItemId(id);
    this.router.navigate(['admin', 'students', 'edit']);
    // this.router.navigate(['admin','students', 'edit', student.id]);
  }

  viewStudent(id: string) {
    this.studentService.setSelectedItemId(id);
    this.router.navigate(['admin', 'students', 'about']);
    // this.router.navigate(['admin','students', 'about', student.id]);
  }
  
    ngOnInit(): void { 
      this.retrieveStudents(this.pageIndex, this.pageSize,this.keyword);
    }

    students: Student[]=[];
    // currentTeacher: Teacher ={} ;
    currentIndex = -1;
    title ?:string;
    keyword = "";
  
    retrieveStudents(pageIndex: number, pageSize: number,keyword:string): void {
      // this.students = this.samepleStudents;
      this.studentService.getStudentPage(pageIndex, pageSize,keyword)
        .subscribe({
          next: (res) => {
            this.students = res.data.content;
            this.length = res.data.totalElements;
          },
          error: (e) => console.error(e)
        });
    }
  
    refreshList(): void {
      this.retrieveStudents(this.pageIndex, this.pageSize,this.keyword);
      // this.currentTeacher = {};
      this.currentIndex = -1;
    }
  
    setActiveStudent(student: Student, index: number): void {
      // this.currentTeacher = teacher;
      this.currentIndex = index;
    }
 

          
  /**
   * Write code on Method
   *
   * @return response()
   */
  removeStudent(id: number|null) {
    if(id!=null){
      this.studentService.deleteStudent(id).subscribe(() => {
        this.students = this.students.filter(item => item.id !== id);
        console.log('Student deleted successfully!');
      }, (error: HttpErrorResponse) => {
        console.error('Error deleting student:', error);
      });
    }
    else{
    alert("Id must be not null");
    }
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
    this.retrieveStudents(this.pageIndex, this.pageSize,this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  search(): void {
    console.log("data search",this.keyword)
    this.pageIndex = 0;
    this.retrieveStudents(this.pageIndex, this.pageSize,this.keyword);

  }
}
