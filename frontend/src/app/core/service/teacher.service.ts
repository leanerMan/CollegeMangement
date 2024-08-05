import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../models/api-response';
import { environment } from '../../../environments/environment.development';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  getTest() : Observable<any>{
    return this.http.get<any>(`${environment.apiUrlMe}test/admin`);
  }

  constructor(private http: HttpClient) { }

  // Create a new teacher
  addTeacher(teacher: any): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}teachers/`, teacher);
  }

  // Create a new teacher
  addTeacherWithPhoto(teacher: any): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}teachers/withPhoto`, teacher);
  }

  // Get all teachers
  getTeachers(page: number, size: number, keyword: string): Observable<ApiResponse> {
    if (keyword != "") {
      keyword = keyword.toLowerCase();
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}teachers/search?page=${page}&size=${size}&keyword=${keyword}`);
    } else {
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}teachers/page?page=${page}&size=${size}`);
    }

  }

  // Get a specific teacher by ID
  getTeacherById(id: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}teachers/${id}`);
  }

  // Update a teacher
  updateTeacher(teacher: any,id:any): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${environment.apiUrlAbhi}teachers/${id}`, teacher);
  }

  // Delete a teacher by ID
  deleteTeacher(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrlAbhi}teachers/${id}`);
  }

  setSelectedItemId(id: string) {
    localStorage.clear();
    localStorage.setItem('selectedTeacherId', id);
  }

  getSelectedItemId(): any {
    let id = localStorage.getItem('selectedTeacherId');
    if (id) {
      return id;
    } else {
      return null
    }
  }
}
