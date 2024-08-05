import { Component } from '@angular/core';
import { Department } from '../../../models/department.model';
import { DepartmentService } from '../../../core/service/department.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';


@Component({
  selector: 'app-add-department',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatDatepickerModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl:'./add-department.component.html',
  styleUrl: './add-department.component.css'
})
export class AddDepartmentComponent {

  department: Department={
    id: null,
    department: "",
    headOfDepartment: "",
    phone: "",
    email: "",
    departmentStartDate: "",
    studentCapacity: 0,
    details: "",
   
  };

  constructor(private router: Router, private departmentService: DepartmentService) {}

  ngOnInit(): void {

  }

  submitted = false;


  saveDepartment(): void {
    const data : Department= {
      id: this.department.id,
      department: this.department.department,
      headOfDepartment: this.department.headOfDepartment,
      phone: this.department.phone,
      email: this.department.email,
      departmentStartDate: this.department.departmentStartDate.toISOString().slice(0, 10),
      studentCapacity:this.department.studentCapacity,
      details: this.department.details,
    };
    console.log(data);

    const formData = new FormData();
    formData.append('data', JSON.stringify(data)); 
 
    console.log(formData.get('data')); 
    console.log(formData.get('file'));

    this.departmentService.addDepartment(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          alert('Record Saved Successfully!!');
          this.router.navigate(['admin','department', 'all']);
        },
        error: (e) => {
          alert('An error occurred while saving the record!');
          console.error(e);
        }
      });
  }

  newDepartment(): void {
    this.submitted = false;
    this.department = {
      id: 0,
      department: "",
      headOfDepartment:"",
      phone: "",
      email: "",
      departmentStartDate: "",
      studentCapacity: 0,
      details: "",
    };
  }

  cancel(): void {
  }
}
