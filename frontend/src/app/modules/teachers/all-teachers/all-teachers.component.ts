import { Component, Inject, OnInit } from '@angular/core';
import { TeacherService } from '../../../core/service/teacher.service';
import { Teacher } from '../../../models/teacher';
import { Router } from '@angular/router';
import { PageEvent, MatPaginatorModule } from '@angular/material/paginator';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DatePipe } from '@angular/common';
import swal from 'sweetalert';
import { FormsModule } from '@angular/forms';
import { BreadcrumbComponent } from '../../../shared/breadcrumb/breadcrumb.component';


@Component({
  selector: 'app-all-teachers',
  standalone: true,
  imports: [MatPaginatorModule, MatTooltipModule, DatePipe,FormsModule,BreadcrumbComponent],
  templateUrl: './all-teachers.component.html',
  styleUrl: './all-teachers.component.css'
})
export class AllTeachersComponent implements OnInit {



  teachers: Teacher[] = [];
  // currentTeacher: any;
  currentIndex = -1;
  keyword = "";

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

  constructor(private router: Router, private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.retrieveTeachers(this.pageIndex, this.pageSize,this.keyword);
  }


  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.retrieveTeachers(this.pageIndex, this.pageSize,this.keyword);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

test(){
  this.teacherService.getTest().subscribe({
    next: (res) => {
      console.log(res);

    },
    error: (e) => {console.log(e);}
  });
}

  retrieveTeachers(pageIndex: number, pageSize: number,keyword:string): void {
    // swal("Good job!", "You clicked the button!", "success");
    this.teacherService.getTeachers(pageIndex, pageSize,keyword)
      .subscribe({
        next: (res) => {
          console.log(res.data);
          this.teachers = res.data.content;
          this.length = res.data.totalElements;

        },
        error: (e) => console.error(e)
      });
  }
  addTeacher() {
    this.router.navigate(['admin', 'teachers', 'add']);
  }

  // editTeacher(teacher: Teacher) {
  //   console.log("here")
  //   // Navigate to the edit page with the teacher's ID
  //   console.log(teacher);
  //   this.sharedService.setSelectedItem(teacher);
  //   this.router.navigate(['home', 'teachers', 'edit']);
  // }

  editTeacher(id: string) {
    this.teacherService.setSelectedItemId(id);
    this.router.navigate(['admin', 'teachers', 'edit']);
  }

  aboutTeacher(id: string) {
    this.teacherService.setSelectedItemId(id);
    // this.router.navigate(['home', 'teachers', 'edit']);
    this.router.navigate(['admin', 'teachers', 'about']);
  }

  refreshList(): void {
    this.retrieveTeachers(this.pageIndex, this.pageSize,this.keyword);
    // this.currentTeacher = {};
    this.currentIndex = -1;
  }

  removeTeacher(teacher: Teacher): void {
    swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover!",
      icon: "warning",
      buttons: ['cancel', 'yes'],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          if (teacher.id)
            this.teacherService.deleteTeacher(teacher.id)
              .subscribe({
                next: (res) => {
                  console.log(res);
                  this.refreshList();
                },
                error: (e) => console.error(e)
              });
          swal("Teacher has been deleted!", {
            icon: "success",
          });
        }
      });
  }

  search(): void {
    console.log("data search",this.keyword)
    this.pageIndex = 0;
    this.retrieveTeachers(this.pageIndex, this.pageSize,this.keyword);

  }


}

