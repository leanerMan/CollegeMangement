import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TeacherService } from '../../../core/service/teacher.service';
import { Router } from '@angular/router';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { Teacher } from '../../../models/teacher';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';


@Component({
  selector: 'app-edit-teacher',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule],
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './edit-teacher.component.html',
  styleUrl: './edit-teacher.component.css'
})
export class EditTeacherComponent implements OnInit {

  selectedId:string | null | undefined 
  selectedTeacher: Teacher | undefined;
  submitted = false;

  file: File | null = null;
  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;


  constructor(private router: Router, private teacherService: TeacherService) { }

  ngOnInit() {
    this.selectedId = this.teacherService.getSelectedItemId();
    if (this.selectedId==null || this.selectedId==undefined) {
      this.router.navigate(['admin','teachers','all'])
    } else {
      this.teacherService.getTeacherById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedTeacher = res.data
          if(this.selectedTeacher){
            console.log(this.selectedTeacher.profilePhoto);
            if (this.selectedTeacher.profilePhoto != undefined && this.selectedTeacher.profilePhoto != null && this.selectedTeacher.profilePhoto != "") {
              this.imageSrc = 'https://cargofile.s3.ap-south-1.amazonaws.com/teachers/' + this.selectedTeacher.profilePhoto;
            } else {
              this.imageSrc = this.defaultImage;
            }
      
          }

        },
        error: (e) => console.error(e)
      });

 
    }
 

   
  }
  onDateReturn(event: any): any {
    // Convert Moment object to JavaScript Date object
    return event.format('YYYY-MM-DD');
  }

  saveTeacher(): void {
    if (this.selectedTeacher) {
    this.submitted = true;
    console.log(this.selectedTeacher);
    // this.selectedTeacher.joiningDate = this.selectedTeacher.joiningDate.toISOString().slice(0, 10);

    const formData = new FormData();
    this.selectedTeacher.joiningDate= (typeof this.selectedTeacher.joiningDate === 'string') ?  this.selectedTeacher.joiningDate : this.onDateReturn(this.selectedTeacher.joiningDate),
      // Append form data to FormData object
      Object.entries(this.selectedTeacher).forEach(([key, value]) => {
        formData.append(key, value);
      });

      if (this.file) {
        formData.append('profilePhotoFile', this.file)
      }
 



    // this.teacher.joiningDate=this.teacher.joiningDate?.toLocaleDateString();
    this.teacherService.updateTeacher(formData,this.selectedId)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          alert(res.message);
          this.router.navigate(['admin', 'teachers', 'all']);
        },
        error: (e) => {
          console.error(e)
          alert(e.error.message)
        }
      });
    }
  }


  onFileChange(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const files: FileList = event.target.files;
      if (files.length > 0) {
        this.file = files[0];
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
    if(this.selectedTeacher){
      this.imageSrc = 'https://cargofile.s3.ap-south-1.amazonaws.com/teachers/' + this.selectedTeacher.profilePhoto;
      this.imageUploaded = false;
      // Clear the file input
      const input = document.getElementById('imag') as HTMLInputElement;
      if (input) {
        input.value = '';
      }
    }
    }
   
}
