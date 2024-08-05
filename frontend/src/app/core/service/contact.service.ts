import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../models/api-response';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private http=inject(HttpClient);

// Create a new contact
addcontact(contact: any): Observable<ApiResponse> {
  return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}contacts/`, contact);
}

// Create a new contact
addcontactWithPhoto(contact: any): Observable<ApiResponse> {
  return this.http.post<ApiResponse>(`${environment.apiUrlAbhi}contacts/withPhoto`, contact);
}

// Get all contacts
getcontacts(page: number, size: number, keyword: string): Observable<ApiResponse> {
  if (keyword != "") {
    keyword = keyword.toLowerCase();
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}contacts/search?page=${page}&size=${size}&keyword=${keyword}`);
  } else {
    return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}contacts/page?page=${page}&size=${size}`);
  }

}

// Get a specific contact by ID
getcontactById(id: string): Observable<ApiResponse> {
  return this.http.get<ApiResponse>(`${environment.apiUrlAbhi}contacts/${id}`);
}

// Update a contact
updatecontact(contact: any,id:any): Observable<ApiResponse> {
  return this.http.put<ApiResponse>(`${environment.apiUrlAbhi}contacts/${id}`, contact);
}

// Delete a contact by ID
deletecontact(id: string): Observable<void> {
  return this.http.delete<void>(`${environment.apiUrlAbhi}contacts/${id}`);
}

setSelectedItemId(id: string) {
  localStorage.clear();
  localStorage.setItem('selectedcontactId', id);
}

getSelectedItemId(): any {
  let id = localStorage.getItem('selectedcontactId');
  if (id) {
    return id;
  } else {
    return null
  }
}
}
