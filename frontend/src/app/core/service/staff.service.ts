import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Staff } from "../../models/staff";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/api-response";
import { environment } from "../../../environments/environment.development";

@Injectable({
    providedIn: 'root'
  })
  export class StaffService {

    constructor(private http: HttpClient) {}


    
    addStaff(formData: any): Observable<Staff> {
      const headers = new HttpHeaders();
      formData.forEach((value: any, key: any) => {
        console.log(key, value);
      });
      
      return this.http.post<Staff>(`${environment.apiUrlUma}staffs/withphoto`, formData, { headers: headers });

    }


    //to retrive all teacher
    getStaffList(page:number,size:number,keyword:string): Observable<any> {
      if(keyword!=""){
        return this.http.get<ApiResponse>(`${environment.apiUrlUma}staffs/search?search=${keyword}&page=${page}&size=${size}`)
      } else {
        return this.http.get<ApiResponse>(`${environment.apiUrlUma}staffs/?page=${page}&size=${size}`);
      }
    }
    
    getstaff(id: number): Observable<ApiResponse> {
      // Append the id to the API endpoint
      const url = `${environment.apiUrlUma}staffs/${id}`;
  
      // Make the HTTP GET request to fetch the staff details by id
      return this.http.get<ApiResponse>(url);
    }

  deletestaff(id: string): Observable<ApiResponse> {
    const url = `${environment.apiUrlUma}staffs/${id}`;
    return this.http.delete<ApiResponse>(url);
  }

 
  updatestaff(formData: any): Observable<Staff> {
    const id = formData.get('id');
    const headers = new HttpHeaders();
    formData.forEach((value: any, key: any) => {
      console.log(key, value);
    });
    const url = `${environment.apiUrlUma}staffs/withphoto/${id}`;
  
    return this.http.put<Staff>(url, formData,{ headers: headers });
  }

  search(searchTerm: string): Observable<any> {
    // Construct the URL for your search API endpoint
    const url = `${environment.apiUrlUma}staffs/search/${searchTerm}`;

    // Make an HTTP GET request to the constructed URL
    return this.http.get<any>(url);
  }

  }