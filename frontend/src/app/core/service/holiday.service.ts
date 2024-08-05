import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../models/api-response';
import { Holiday } from '../../models/holiday';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class HolidayService {

  constructor(private http:HttpClient) { }

  //create holiday
  addHoliday(holiday:Holiday): Observable<ApiResponse>{
   return  this.http.post<ApiResponse>(`${environment.apiUrlUma}holidays/`,holiday);
  }

  //all holiday list
  getAllHoliday(page:number,size:number,keyword:string): Observable<ApiResponse>{
    if(keyword!=""){
      return this.http.get<ApiResponse>(`${environment.apiUrlUma}holidays/search?search=${keyword}&page=${page}&size=${size}`)
    }else{
      return this.http.get<ApiResponse>(`${environment.apiUrlUma}holidays/?page=${page}&size=${size}`)
    }
  }

  //get Holiday by Id
  getHolidayById(id:string): Observable<ApiResponse>{
    return this.http.get<ApiResponse>(`${environment.apiUrlUma}holidays/${id}`);
  }

  //delete holiday
  removeHoliday(id:string) : Observable<ApiResponse>{
     return this.http.delete<ApiResponse>(`${environment.apiUrlUma}holidays/${id}`)
  }

  setSelectedItemId(id: string) {
    localStorage.clear();
    localStorage.setItem('selectedHolidayId', id);
  }

  getSelectedItemId() {
    
    let id = localStorage.getItem('selectedHolidayId');
    if (id) {
      return id;
    } else {
      return null
    }
  }

  


}
