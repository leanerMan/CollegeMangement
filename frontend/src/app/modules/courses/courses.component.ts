import { Component } from '@angular/core';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [AllCoursesComponent,RouterModule,RouterLink],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.css'
})
export class CoursesComponent {

}
