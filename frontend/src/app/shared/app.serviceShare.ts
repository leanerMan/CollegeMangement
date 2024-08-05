import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private lable1 = new BehaviorSubject<any>('Dashboard');
  private lable2 = new BehaviorSubject<any>(null);

  sharedData1$ = this.lable1.asObservable();
  sharedData2$ = this.lable2.asObservable();

  updateSharedData(data1: any, data2: any) {
    this.lable1.next(data1);
    this.lable2.next(data2);
  }
}
