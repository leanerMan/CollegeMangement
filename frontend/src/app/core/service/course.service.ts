import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../models/api-response';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = 'http://localhost:8282/api/v1/courses';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  constructor(private http: HttpClient) {}

// Create a new Course
addCourse(course: any): Observable<any> {
  return this.http.post<any>(`${this.apiUrl}/`, course);
}

// Get all Courses
getCourses(page: number, size: number,keyword:string): Observable<any> {
  if (keyword != "") {
    keyword = keyword.toLowerCase();
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}courses/search?page=${page}&size=${size}&keyword=${keyword}`);
  } else {
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}courses/?page=${page}&size=${size}`);
  }

}

// Get a specific Course by ID
getCoursesById(id: String): Observable<any> {
  return this.http.get<any>(`${this.apiUrl}/${id}`);
}

 // Update a Course
 updateCourse(course: any): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/${course.id}`, course);
}

// Delete a Course by ID
deleteCourse(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

setSelectedItemId(id: string) {
  localStorage.clear();
  localStorage.setItem('selectedCourseId', id);
}

getSelectedItemId(): any {
  let id = localStorage.getItem('selectedCourseId');
  if (id) {
    return id;
  } else {
    return null
  }
}

}


