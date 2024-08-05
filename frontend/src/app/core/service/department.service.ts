import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Department } from '../../models/department.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class DepartmentService {
  private apiUrl = 'http://localhost:8282/api/v1/departments';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  constructor(private http: HttpClient) {}

// Create a new Department
addDepartment(department: Department): Observable<Department> {
  return this.http.post<Department>(`${this.apiUrl}/`, department);
}

// Get all Departments
getDepartments(): Observable<any> {
  return this.http.get<any>(`${this.apiUrl}/`);
}

// Get a specific Department by ID
getDepartmentById(id: String): Observable<any> {
  return this.http.get<any>(`${this.apiUrl}/${id}`);
}

 // Update a Department
 updateDepartment(department: Department): Observable<Department> {
  return this.http.put<Department>(`${this.apiUrl}/${department.id}`, department);
}

// Delete a Department by ID
deleteDepartment(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

setSelectedItemId(id: string) {
  localStorage.clear();
  localStorage.setItem('selectedDeptId', id);
}

getSelectedItemId(): any {
  let id = localStorage.getItem('selectedDeptId');
  if (id) {
    return id;
  } else {
    return null
  }
}

}
