import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Library } from '../../models/library';

import { environment } from '../../../environments/environment.development';
import { ApiResponse } from '../../models/api-response';


@Injectable({
  providedIn: 'root'
})
export class LibraryService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http:HttpClient) { }
  
    // Get all Library
    getLibrary(): Observable<any> {
      return this.http.get<any>(`${environment.apiUrlAbhi}library/`);
    }

    // Add  Library
    addLibrary(library:Library):Observable<ApiResponse> {
      return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}library/`,library);
    
    }
    //GET BY ID
    getLibraryById(id: string): Observable<ApiResponse> {
      return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}library/${id}`);
    }
    
    // Edit  Library
    editLibrary(id: string, library: Library):Observable<ApiResponse> {
      return this.http.put<ApiResponse>(`${environment.apiUrlAbhi}library/${id}`, library);
    }
  
    // Delete  Library
    deleteLibrary(id: number): Observable<void> {
      return this.http.delete<void>(`${environment.apiUrlAbhi}library/${id}`);
    }


}

