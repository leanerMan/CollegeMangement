import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StaffService } from '../../../core/service/staff.service';
import { FormsModule } from '@angular/forms';
import { Staff } from '../../../models/staff';
import swal from 'sweetalert';

@Component({
  selector: 'app-edit-staff',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-staff.component.html',
  styleUrl: './edit-staff.component.css'
})
export class EditStaffComponent {

  id!: number; 
  staff: any = {};
  constructor(private staffservice:StaffService,private route: ActivatedRoute,private router: Router) { }
 
  ngOnInit(): void {
    this.EditStaffComponent();
  }

  EditStaffComponent(){
    this.route.params.subscribe(params => {
      // Access the id parameter from the route
      this.id = params['id']; 

      console.log("ID:", this.id);
    });
    if (this.id !== undefined) {
    this.staffservice.getstaff(this.id)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.staff = res.data;  
       // this.staff.profilePhoto = JSON.parse(res.data.profilePhoto);

      },
      error: (e) => console.error(e)
    });
  }
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
onupdate() {
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

  const formData = new FormData();
  Object.entries(data).forEach(([key, value]) => {
    formData.append(key, value); // Add other student data to FormData
});

if (this.file) {
  formData.append('profilePhotoFile', this.file);
}

   console.log(formData)

  this.staffservice.updatestaff(formData).subscribe({
    next: (res) => {
      console.log(res);
      swal({
        icon: 'success',
        title: 'Success',
        text: 'Staff has been updated successfully!',
      }).then(() => {
        this.router.navigate(['/home/staffs/all']);
      });
    },
    error: (e) => {
   
     
    }
  });
}

close(){
  this.router.navigate(['/home/staffs/all']);
}
 
}
