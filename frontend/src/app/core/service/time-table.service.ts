import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { TimeTable } from '../../models/time-table';
import { ApiResponse } from '../../models/api-response';

@Injectable({
  providedIn: 'root'
})
export class TimeTableService {

  constructor(private http: HttpClient) { }

  //create time Table
  addTimeTable(timetable:TimeTable): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(`${environment.apiUrlUma}timetable/`,timetable)
  }

  //get data by id
  getTimeTableById(id:string) :Observable<ApiResponse>{
    return this.http.get<ApiResponse>( `${environment.apiUrlUma}timetable/${id}`)
  }


  //Get All data of Time table
  getTimeTable(page:number,size:number,keyword:string):Observable<ApiResponse>{ 
    if(keyword!=""){
      return this.http.get<ApiResponse>(`${environment.apiUrlUma}timetable/search?search=${keyword}&page=${page}&size=${size}`)
    } else {
      return this.http.get<ApiResponse>(`${environment.apiUrlUma}timetable/?page=${page}&size=${size}`);
    }
  }

  //delete data by id
  removeTimeTable(id:string):Observable<ApiResponse>{
    return this.http.delete<ApiResponse>(`${environment.apiUrlUma}timetable/${id}`);
  }

  //update data by id
  updateTimeTable(timeTable:TimeTable,id:string): Observable<ApiResponse>{
    return this.http.put<ApiResponse>(`${environment.apiUrlUma}timetable/${id}`,timeTable);
  }

  //search data
  searchTimeTable(keyword:string):Observable<ApiResponse>{
    return this.http.get<ApiResponse>(`${environment.apiUrlUma}timetable/search/${keyword}`)
  }

  setSelectedItemId(id: string) {
    localStorage.clear();
    localStorage.setItem('selectedTimeTableId', id);
  }

  getSelectedItemId(): any {
    let id = localStorage.getItem('selectedTimeTableId');
    if (id) {
      return id;
    } else {
      return null
    }
  }


}
