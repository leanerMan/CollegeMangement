import { Component } from '@angular/core';
import { AllDepartmentsComponent } from './all-departments/all-departments.component';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [AllDepartmentsComponent,RouterModule,RouterLink],
  templateUrl: './departments.component.html',
  styleUrl: './departments.component.css'
})
export class DepartmentsComponent {

}
