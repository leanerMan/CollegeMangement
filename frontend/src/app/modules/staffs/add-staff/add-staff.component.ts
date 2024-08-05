import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Staff } from '../../../models/staff';
import { StaffService } from '../../../core/service/staff.service';
import swal from 'sweetalert';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-add-staff',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './add-staff.component.html',
  styleUrl: './add-staff.component.css'
})
export class AddstaffComponent {

  staff: Staff = {
    id: '',
    firstName: '',
    lastName: '',
    gender: '',
    mobile: '',
    designation: '',
    departments: '',
    email: '',
    joiningDate: '',
    profilePhoto: '',
    address: '',
    education: ''
  };



  constructor( private staffservice: StaffService,private router: Router) {}

   ngOnInit(): void {
    
  }




  file?: File ;

  handleFileInput(event: any) {
    const files: FileList = event.target.files;
    if (files.length > 0) {
      this.file = files[0];
    } else {
      this.file = undefined;
    }

  }
  
  onSubmit(myForm: NgForm) {
    if(myForm.valid){
       
    const data: Staff = {
      firstName: this.staff.firstName,
      lastName: this.staff.lastName,
      gender: this.staff.gender,
      mobile: this.staff.mobile,
      designation: this.staff.designation,
      departments: this.staff.departments,
      email: this.staff.email,
      joiningDate: this.staff.joiningDate,
      profilePhoto: '',
      address: this.staff.address,
      education: this.staff.education,
      id: this.staff.id,
    };
    console.log(data);
    const formData = new FormData();
    Object.entries(data).forEach(([key, value]) => {
      formData.append(key, value); // Add other student data to FormData
  });

  if (this.file) {
    formData.append('profilePhotoFile', this.file);
  }

     console.log(formData)
  
    this.staffservice.addStaff(formData).subscribe({
      next: (res) => {
        console.log(res);
        swal({
          icon: 'success',
          title: 'Success',
          text: 'Staff has been added successfully!',
        }).then(() => {
          this.router.navigate(['/home/staffs/all']);
        });
      
      },
      error: (e) => {
        console.error(e);
        swal({
          icon: 'error',
          title: 'Error',
          text: 'An error occurred while saving the data.',
        });
       
      }
    });
  }else{
    swal({
      icon: 'error',
      title: 'Error',
      text: 'Kindly fill all the fields.',
    });
  }
  }

  close(){
    this.router.navigate(['/home/staffs/all']);
  }

    }

  


