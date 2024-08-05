import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import {provideNativeDateAdapter} from '@angular/material/core';
// import {MatDatepickerModule} from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { Student } from '../../../models/student';
import { StudentService } from '../../../core/service/student.service';
import { MY_FORMATS } from '../../../_helper/MyFormat';



@Component({
  selector: 'app-edit-student',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule],
  templateUrl: './edit-student.component.html',
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  styleUrl: './edit-student.component.css'
})
export class EditStudentComponent implements OnInit{
cancel() {
// throw new Error('Method not implemented.');
}

  studentId?: string ;

  file?: File ;
  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;
  student: Student| undefined;

  constructor(private router1: Router,private route: ActivatedRoute, private studentService: StudentService) {}

  ngOnInit() {

    this.studentId = this.studentService.getSelectedItemId();
    if (this.studentId==null || this.studentId==undefined) {
      this.router1.navigate(['admin','students','all'])
    } else {
      this.studentService.getStudentById(this.studentId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.student = res.data
          if(this.student){
            console.log(this.student.profilePhoto);
            if (this.student.profilePhoto != undefined && this.student.profilePhoto != null && this.student.profilePhoto != "") {
              this.imageSrc = 'https://cargofile.s3.ap-south-1.amazonaws.com/students/' + this.student.profilePhoto;
            } else {
              this.imageSrc = this.defaultImage;
            }
      
          }

        },
        error: (e) => console.error(e)
      });
    }

  }

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
    if(this.student)
    this.imageSrc ='https://cargofile.s3.ap-south-1.amazonaws.com/students/'+this.student.profilePhoto;;
    this.imageUploaded = false;
    // Clear the file input
    const input = document.getElementById('imag') as HTMLInputElement;
    if (input) {
      input.value = '';
    }
  }

  onDateReturn(event: any): any {
    // Convert Moment object to JavaScript Date object
    return event.format('YYYY-MM-DD');
  }


  updateStudent() {
    if (this.student) {

      console.log(this.student);
     
  
      const formData = new FormData();
      this.student.admissionDate= (typeof this.student.admissionDate === 'string') ?  this.student.admissionDate : this.onDateReturn(this.student.admissionDate),
        // Append form data to FormData object
        Object.entries(this.student).forEach(([key, value]) => {
          formData.append(key, value);
        });
  
        if (this.file) {
          formData.append('profilePhotoFile', this.file)
        }

      this.studentService.updateStudent(formData,this.studentId)
        .subscribe({
          next: (res) => {
            console.log(res);
            // this.submitted = true;
            alert(res);
            this.router1.navigate(['admin', 'students', 'all']);
          },
          error: (e) => {
            console.error(e)
            alert(e.error.message)
          }
        });
      }
    }
    

  


  aadharflag?: boolean;

  chkAadhar(): void {
    if (this.student && this.student.aadhaarNo != null) {
      if (isNaN(Number(this.student.aadhaarNo))) {
        this.student.aadhaarNo = "";
      }
      if (this.student.aadhaarNo && this.student.aadhaarNo.length < 12) {
        alert("Please enter a 12-digit valid Aadhar Number");
        this.aadharflag = true;
      } else {
        const D = [
          [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
          [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
          [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
          [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
          [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
          [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
          [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
          [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
          [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
          [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        ];
        const inV = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];
        const P = [
          [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
          [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
          [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
          [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
          [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
          [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
          [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
          [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
        ];
        const digits = this.student.aadhaarNo.split('');
        const len = digits.length - 1;
        let i = 0;
        let c = 0;
        for (let j = len; j > -1; j--) {
          const imod8 = i % 8;
          const p = P[imod8][parseInt(digits[j])];
          c = D[c][p];
          i++;
        }
        if (c === 0) {
          this.aadharflag = false;
          console.log('valid aadhar' + this.student.aadhaarNo);
        } else {
          alert("This is Not Valid Aadhaar Number");
          this.aadharflag = true;
        }
      }
    }
  }



  fillAddress(val:any) :void {


      if(this.student){
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
      }
     
  };
  fetchAddress(val:any,valstatus:string): void {

      this.studentService.fetchAddress(val)
      .subscribe({
        next: (res) => {
          if(res[0].Status=='Success'){
            if(this.student){
              if(valstatus=='c'){
                this.student.cCity = res[0].PostOffice[0].District;
                this.student.cState =res[0].PostOffice[0].State;
              }
              else if(valstatus=='p'){
                this.student.pCity = res[0].PostOffice[0].District;
                this.student.pState =res[0].PostOffice[0].State;
              }
            }
  
          
          console.log(res);
        }else if(res[0].Status=='Error'){
         alert('Please enter correct pin!!');
        }
        },
        error: (e) => console.error(e)
      });
    
   
  };


  

}
