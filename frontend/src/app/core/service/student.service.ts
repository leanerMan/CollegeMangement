import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../../models/student';
import { ApiResponse } from '../../models/api-response';
import { environment } from '../../../environments/environment.development';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class StudentService {


  constructor(private http: HttpClient) { }

  // Create a new Student
  addStudent(formData: any): Observable<ApiResponse> {
    console.log("FormData entries:");
    formData.forEach((value: any, key: any) => {
      console.log(key, value);
    });
    // return this.http.post<Student>(`${this.apiUrl}/students/`, student);
    return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}students/`, formData);

  }

  // Get all Students
  getStudents(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/`);
  }

  // Get a specific Student by ID
  getStudentById(id: any): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/${id}`);
  }

  // Update a Student
  // Update a Student
  updateStudent(formData: any,id:any): Observable<ApiResponse> {
    console.log("FormData entries:");
    formData.forEach((value: any, key: any) => {
      console.log(key, value);
    });
    return this.http.put<ApiResponse>(`${environment.apiUrlAbhi}students/${id}`, formData);
  }

  // Delete a Student by ID
  deleteStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrlAbhi}students/${id}`);
  }

  fetchAddress(val: any): Observable<any> {
    return this.http.get<any>(`https://api.postalpincode.in/pincode/${val}`)
  }

  getStudentPage(page: number, size: number, keyword: string): Observable<ApiResponse> {
    if (keyword != "") {
      keyword = keyword.toLowerCase();
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/search?page=${page}&size=${size}&keyword=${keyword}`);
    } else {
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/page?page=${page}&size=${size}`);
    }

  }

  getStudentAttendance(page: number, size: number, yearMonth: string): Observable<ApiResponse> {
    if (yearMonth != "") {
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/attendance?page=${page}&size=${size}&yearMonth=${yearMonth}`);
    } else {
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}students/attendance?page?page=${page}&size=${size}`);
    }

  }

  setSelectedItemId(id: string) {
    localStorage.clear();
    localStorage.setItem('selectedStudentId', id);
  }

  getSelectedItemId(): any {
    let id = localStorage.getItem('selectedStudentId');
    if (id) {
      return id;
    } else {
      return null
    }
  }

  //   /**
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   getAll(): Observable<any> {

  //     return this.httpClient.get(this.apiURL + '/posts/')

  //     .pipe(
  //       catchError(this.errorHandler)
  //     )
  //   }

  //   /**
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   create(post:Post): Observable<any> {

  //     return this.httpClient.post(this.apiURL + '/posts/', JSON.stringify(post), this.httpOptions)

  //     .pipe(
  //       catchError(this.errorHandler)
  //     )
  //   }  

  //   /**
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   find(id:number): Observable<any> {

  //     return this.httpClient.get(this.apiURL + '/posts/' + id)

  //     .pipe(
  //       catchError(this.errorHandler)
  //     )
  //   }

  //   /**
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   update(id:number, post:Post): Observable<any> {

  //     return this.httpClient.put(this.apiURL + '/posts/' + id, JSON.stringify(post), this.httpOptions)

  //     .pipe( 
  //       catchError(this.errorHandler)
  //     )
  //   }

  //   /**
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   delete(id:number){
  //     return this.httpClient.delete(this.apiURL + '/posts/' + id, this.httpOptions)

  //     .pipe(
  //       catchError(this.errorHandler)
  //     )
  //   }

  //   /** 
  //    * Write code on Method
  //    *
  //    * @return response()
  //    */
  //   errorHandler(error:any) {
  //     let errorMessage = '';
  //     if(error.error instanceof ErrorEvent) {
  //       errorMessage = error.error.message;
  //     } else {
  //       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  //     }
  //     return throwError(errorMessage);
  //  }
}
