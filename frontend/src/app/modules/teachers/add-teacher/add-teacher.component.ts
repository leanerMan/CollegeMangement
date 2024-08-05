import { Component } from '@angular/core';
import {  FormsModule, NgModel } from '@angular/forms';
import { Teacher } from '../../../models/teacher';
import { TeacherService } from '../../../core/service/teacher.service';
import {  Router } from '@angular/router';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';
import { BreadcrumbComponent } from '../../../shared/breadcrumb/breadcrumb.component';


@Component({
  selector: 'app-add-teacher',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatDatepickerModule,BreadcrumbComponent],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-teacher.component.html',
  styleUrl: './add-teacher.component.css'
  
})
export class AddTeacherComponent {


  submitted= false ;

  file:File | null = null; 

  teacher: Teacher={
    firstName: '',
    lastName: '',
    gender: "",
    mobile: '',
    designation: '',
    departments: '',
    email: '',
    joiningDate: null,
    address: '',
    education: '',
    id: null,
    profilePhoto:'',
    degree:''
  };

  constructor( private teacherService: TeacherService,private router:Router) {}

  ngOnInit(): void {
  
  }

  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;

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
    this.imageSrc = this.defaultImage;
    this.imageUploaded = false;
    // Clear the file input
    const input = document.getElementById('imag') as HTMLInputElement;
    if (input) {
      input.value = '';
    }
  }




  saveTeacher(): void {
    this.submitted = true;
    console.log(this.teacher);
    this.teacher.joiningDate=this.teacher.joiningDate.toISOString().slice(0, 10);

    const formData = new FormData();

    // Append form data to FormData object
    Object.entries(this.teacher).forEach(([key, value]) => {
      if(key!=='id'){
        formData.append(key, value);
      }
    });

    if(this.file){
      formData.append('profilePhotoFile',this.file)
    }
  
    // this.teacher.joiningDate=this.teacher.joiningDate?.toLocaleDateString();
    this.teacherService.addTeacherWithPhoto(formData)
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


  // onFileSelected(event: any) {
  //   this.file = event.target.files[0];
  //   // this.teacher.profilePhotoFile = file;
  // }




}
