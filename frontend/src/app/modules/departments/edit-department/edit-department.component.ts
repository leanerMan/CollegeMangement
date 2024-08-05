import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from '../../../core/service/department.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';

@Component({
  selector: 'app-edit-department',
  standalone: true,
  imports: [FormsModule, MatDatepickerModule, MatDatepickerModule],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
  templateUrl: './edit-department.component.html',
  styleUrl: './edit-department.component.css'
})
export class EditDepartmentComponent implements OnInit {

  cancel() {
    this.router.navigate(['admin', 'department', 'all']);
  }

  departmentId?: string;
  department: any;


  constructor(private router: Router, private departmentService: DepartmentService) { }

  ngOnInit() {

    this.departmentId = this.departmentService.getSelectedItemId();

    if (this.departmentId) {
      this.departmentService.getDepartmentById(this.departmentId).subscribe((res) => {
        this.department = res.data;
      });
    }
  }

  onDateReturn(event: any): any {
    return event.format('YYYY-MM-DD');
  }

  updateDepartment() {
    if (this.department != null) {
      this.department.departmentStartDate= (typeof this.department.departmentStartDate === 'string') ?  this.department.departmentStartDate : this.onDateReturn(this.department.departmentStartDate),
      this.departmentService.updateDepartment(this.department).subscribe({
        next: (res) => {
          console.log(res);
          alert('Record Updated Successfully!!');
          this.router.navigate(['admin', 'department', 'all']);
        },
        error: (e) => {
          alert('An error occurred while updating the record!');
          console.error(e);
        }
      });
    }
  }


}
