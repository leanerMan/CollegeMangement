import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { TeacherService } from '../../../core/service/teacher.service';
import { Teacher } from '../../../models/teacher';

@Component({
  selector: 'app-about-teacher',
  standalone: true,
  imports: [],
  templateUrl: './about-teacher.component.html',
  styleUrl: './about-teacher.component.css'
})
export class AboutTeacherComponent implements OnInit {
  selectedId:string | null | undefined 
  selectedTeacher: Teacher | undefined;
  constructor(private route: Router, private teacherService: TeacherService) { }
  profilePhoto='';

  ngOnInit() {
    this.selectedId = this.teacherService.getSelectedItemId();
    if (this.selectedId==null || this.selectedId==undefined) {
      this.route.navigate(['admin','teachers','all'])
    } else {
      this.teacherService.getTeacherById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedTeacher = res.data
          // this.length = res.data.totalElements;

        },
        error: (e) => console.error(e)
      });
    }
    if(this.selectedTeacher){
      this.profilePhoto=this.selectedTeacher.profilePhoto;
    }

  }
}
