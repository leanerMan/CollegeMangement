import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Fee } from "../../models/fee";
import { environment } from "../../../environments/environment.development";
import { ApiResponse } from "../../models/api-response";

@Injectable({
    providedIn: 'root'
  })
export class feeService {

    constructor(private http: HttpClient) {}

    
    addFee(data: Fee): Observable<any> {
        return this.http.post<any>(`${environment.apiUrlUma}/fees/`, data);
      }


      getFeeList(page:number,size:number,keyword:string): Observable<any> {
        if(keyword!=""){
          return this.http.get<ApiResponse>(`${environment.apiUrlUma}/fees/search?search=${keyword}&page=${page}&size=${size}`)
        } else {
          return this.http.get<ApiResponse>(`${environment.apiUrlUma}/fees/?page=${page}&size=${size}`);
        }
      }

      deletefee(id: number): Observable<ApiResponse> {
        const url = `${environment.apiUrlUma}/fees/${id}`;
        return this.http.delete<ApiResponse>(url);
      }

      getfee(id: number): Observable<ApiResponse> {
        // Append the id to the API endpoint
        const url = `${environment.apiUrlUma}/fees/${id}`;
    
        // Make the HTTP GET request to fetch the staff details by id
        return this.http.get<ApiResponse>(url);
      }

      updatefee(data: Fee): Observable<any> {
        console.log(data.id)
        const url = `${environment.apiUrlUma}/fees/`;

        // Remove the <ApiResponse> generic type as it's not needed here
        return this.http.put(url, data);
      }
}
