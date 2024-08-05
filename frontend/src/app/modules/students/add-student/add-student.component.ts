import { Component, NgModule } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { Student } from '../../../models/student';

import { Router, RouterLink } from '@angular/router';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { StudentService } from '../../../core/service/student.service';
import { MY_FORMATS } from '../../../_helper/MyFormat';
// import * as _moment from 'moment';
// // tslint:disable-next-line:no-duplicate-imports
// import {default as _rollupMoment} from 'moment';

// const moment = _rollupMoment || _moment;

@Component({
  selector: 'app-add-student',
  standalone: true,
  imports: [FormsModule,RouterLink,MatDatepickerModule],
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.css'
})



export class AddStudentComponent {

  // date = new FormControl(moment());

  // flightSchedule = {
  //   date: new Date()
  // }

  onDateReturn(event: any): any {
    // Convert Moment object to JavaScript Date object
    return event.format('YYYY-MM-DD');
  }

  student: Student={
    id: null,
    studentName: "",
    fathersName: "",
    mothersName: "",
    mobile: "",
    altMobile: "",
    course: "",
    department: "",
    dob: "",
    gender: "",
    rollNo: null,
    email: "",
    pPin: "",
    pAddress: "",
    pCity: "",
    pState: "",
    cPin: "",
    cAddress: "",
    cCity: "",
    cState: "",
    category: "",
    maritalStatus: "",
    aadhaarNo: "",
    college: "",
    bloodGroup: "",
    admissionDate:null,
    profilePhoto: ""
  };

  constructor(private router: Router, private studentService: StudentService) {}

  ngOnInit(): void {

  }

  submitted = false;
  file?: File ;
 
  // onFileSelected(event: any) {
  //   const files: FileList = event.target.files;
  //   if (files.length > 0) {
  //     this.file = files[0];
  //   } else {
  //     this.file = undefined;
  //   }
  // }



  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;

  onFileChange(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const files: FileList = event.target.files;
      if (files.length > 0) {
            this.file = files[0];
          } else {
            this.file = undefined;
          }
      const reader = new FileReader();
      reader.onload = () => {
        this.imageSrc = reader.result as string;
        this.imageUploaded = true;
      }
      reader.readAsDataURL(files[0]);
    }
  }

  removeImage(): void {
    this.imageSrc = this.defaultImage;
    this.imageUploaded = false;
    // Clear the file input
    const input = document.getElementById('imag') as HTMLInputElement;
    if (input) {
      input.value = '';
    }
  }

  saveStudent(): void {
    const data : Student= {
      id: this.student.id,
      studentName: this.student.studentName,
      fathersName: this.student.fathersName,
      mothersName: this.student.mothersName,
      mobile: this.student.mobile,
      altMobile: this.student.altMobile,
      course: this.student.course,
      department: this.student.department,
      dob: this.onDateReturn(this.student.dob),
      gender: this.student.gender,
      rollNo: this.student.rollNo,
      email: this.student.email,
      pPin: this.student.pPin,
      pAddress: this.student.pAddress,
      pCity: this.student.pCity,
      pState: this.student.pState,
      cPin: this.student.cPin,
      cAddress: this.student.cAddress,
      cCity: this.student.cCity,
      cState: this.student.cState,
      category: this.student.category,
      maritalStatus: this.student.maritalStatus,
      aadhaarNo: this.student.aadhaarNo,
      college: this.student.college,
      bloodGroup: this.student.bloodGroup,
      admissionDate: this.onDateReturn(this.student.admissionDate),
      profilePhoto: this.student.profilePhoto
    };
    console.log(data);
    const formData = new FormData();

    Object.entries(data).forEach(([key, value]) => {
      if(key!=='id'){
        formData.append(key, value);
      }
      // formData.append(key, value); // Add other student data to FormData
  });

    if (this.file) {
      formData.append('profilePhotoFile', this.file);
    }

    this.studentService.addStudent(formData)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          alert('Record Saved Successfully!!');
          this.router.navigate(['admin','students', 'all']);
        },
        error: (e) => {
          alert('An error occurred while saving the record!');
          console.error(e);
        }
      });
  }

  newStudent(): void {
    this.submitted = false;
    this.student = {
      id: null,
      studentName: "",
      fathersName: "",
      mothersName: "",
      mobile: "",
      altMobile: "",
      course: "",
      department: "",
      dob: "",
      gender: "",
      rollNo: null,
      email: "",
      pPin: "",
      pAddress: "",
      pCity: "",
      pState: "",
      cPin: "",
      cAddress: "",
      cCity: "",
      cState: "",
      category: "",
      maritalStatus: "",
      aadhaarNo: "",
      college: "",
      bloodGroup: "",
      admissionDate:null,
      profilePhoto: ""
    };
  }


  fillAddress(val:any) :void {

    if (val.checked) {
      this.student.pAddress = this.student.cAddress;
      this.student.pPin = this.student.cPin;
      this.student.pCity = this.student.cCity;
      this.student.pState = this.student.cState;
    } else {
      this.student.pAddress = "";
      this.student.pPin = "";
      this.student.pCity = "";
      this.student.pState = "";
    }
  };
  fetchAddress(val:any,valstatus:string): void {
    this.studentService.fetchAddress(val)
    .subscribe({
      next: (res) => {
        if(res[0].Status=='Success'){

        if(valstatus=='c'){
          this.student.cCity = res[0].PostOffice[0].District;
          this.student.cState =res[0].PostOffice[0].State;
        }
        else if(valstatus=='p'){
          this.student.pCity = res[0].PostOffice[0].District;
          this.student.pState =res[0].PostOffice[0].State;
        }
        console.log(res);
      }else if(res[0].Status=='Error'){
       alert('Please enter correct pin!!');
      }
      },
      error: (e) => console.error(e)
    });
  };




//   aadharflag?:boolean
//   chkAadhar(): void {
// if(this.student.aadharNo!=null){
//     if (isNaN(this.student.aadharNo)) {
//       this.student.aadharNo = null;
//     }
//     if (this.student.aadharNo!=null && this.student.aadharNo.length < 12) {
//       alert("Please enter 12 digit Valid Aadhar Number");
//       this.aadharflag = true;
//     } else {
//       const D = [
//         [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
//         [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
//         [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
//         [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
//         [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
//         [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
//         [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
//         [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
//         [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
//         [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
//       ];
//       const inV = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];
//       const P = [
//         [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
//         [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
//         [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
//         [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
//         [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
//         [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
//         [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
//         [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
//       ];
//       const digits = this.student.aadharNo.split('');
//       const len = digits.length - 1;
//       let i = 0;
//       let c = 0;
//       for (let j = len; j > -1; j--) {
//         const imod8 = i % 8;
//         const p = P[imod8][parseInt(digits[j])];
//         c = D[c][p];
//         i++;
//       }
//       if (c === 0) {
//         this.aadharflag = false;
//         console.log('valid aadhar' + this.form.aadhaar);
//       } else {
//         alert("This is Not Valid Aadhaar Number");
//         this.aadharflag = true;
//       }
//     }
//   }
//   }
aadharflag?: boolean;

chkAadhar(): void {
  this.aadharflag = false;
  // if (this.student.aadhaarNo != null) {
  //   if (isNaN(Number(this.student.aadhaarNo))) {
  //     this.student.aadhaarNo = "";
  //   }
  //   if (this.student.aadhaarNo && this.student.aadhaarNo.length < 12) {
  //     alert("Please enter a 12-digit valid Aadhar Number");
  //     this.aadharflag = true;
  //   } else {
  //     const D = [
  //       [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
  //       [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
  //       [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
  //       [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
  //       [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
  //       [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
  //       [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
  //       [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
  //       [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
  //       [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
  //     ];
  //     const inV = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];
  //     const P = [
  //       [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
  //       [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
  //       [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
  //       [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
  //       [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
  //       [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
  //       [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
  //       [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
  //     ];
  //     const digits = this.student.aadhaarNo.split('');
  //     const len = digits.length - 1;
  //     let i = 0;
  //     let c = 0;
  //     for (let j = len; j > -1; j--) {
  //       const imod8 = i % 8;
  //       const p = P[imod8][parseInt(digits[j])];
  //       c = D[c][p];
  //       i++;
  //     }
  //     if (c === 0) {
  //       this.aadharflag = false;
  //       console.log('valid aadhar' + this.student.aadhaarNo);
  //     } else {
  //       alert("This is Not Valid Aadhaar Number");
  //       this.aadharflag = true;
  //     }
  //   }
  // }
}






  cancel(): void {
    // Perform your cancel logic here
  }
}

