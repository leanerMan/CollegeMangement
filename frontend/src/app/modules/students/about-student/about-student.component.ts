import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../../../core/service/student.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-about-student',
  standalone: true,
  imports: [],
  templateUrl: './about-student.component.html',
  styleUrl: './about-student.component.css'
})
export class AboutStudentComponent implements OnInit {
  studentId?: string;
  student: any;


  file?: File ;
  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;
  constructor(private route: Router, private studentService: StudentService) { }
  ngOnInit() {
    this.studentId = this.studentService.getSelectedItemId();
    if (this.studentId == null || this.studentId == undefined) {
      this.route.navigate(['admin', 'students', 'all'])
    } else {
      this.studentService.getStudentById(this.studentId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.student = res.data
          if (this.student) {
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
}
