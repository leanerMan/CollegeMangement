import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DepartmentService } from '../../../core/service/department.service';
import { Department } from '../../../models/department.model';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-all-departments',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './all-departments.component.html',
  styleUrl: './all-departments.component.css'
})
export class AllDepartmentsComponent {
addDept() {
  this.router.navigate(['admin','department', 'add']);
}
  constructor(private router: Router,private departmentService: DepartmentService) {}

  editDepartment(department: Department) {
    this.departmentService.setSelectedItemId(department.id+'');
    this.router.navigate(['admin','department', 'edit']);
  }
  
    ngOnInit(): void { 
      this.retrieveDepartments();
    }

    department: Department[]=[];
   
    currentIndex = -1;
    title ?:string;
  
    retrieveDepartments(): void {
      this.departmentService.getDepartments()
        .subscribe({
          next: (response) => {
            this.department = response.data.content;
            console.log("aa gya department",this.department)
            console.log(response,"Data aa gya");
          },
          error: (e) => console.error(e)
        });
    }
  
    refreshList(): void {
      this.retrieveDepartments();
      this.currentIndex = -1;
    }
  
    setActiveDepartment(department: Department, index: number): void {
      this.currentIndex = index;
    }

    removeDepartment(id: number | null) {
      if (id !== null) {
        this.departmentService.deleteDepartment(id).subscribe(
          () => {
            this.department = this.department.filter(item => item.id !== id);
            console.log('Department deleted successfully!');
          },
          (error: HttpErrorResponse) => {
            console.error('Error deleting Department:', error);
          }
        );
      } else {
        alert("Id must not be null");
      }
    }
    

}
