import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Fee } from '../../../models/fee';
import {MatRadioModule} from '@angular/material/radio';
import swal from 'sweetalert';
import { Router } from '@angular/router';
import { feeService } from '../../../core/service/fee.service';

@Component({
  selector: 'app-add-fee',
  standalone: true,
  imports: [FormsModule,CommonModule,MatRadioModule],
  templateUrl: './add-fee.component.html',
  styleUrl: './add-fee.component.css'
})
export class AddFeeComponent {

  fee: Fee = {
    id: 0, // Assuming the initial value for the id is 0
    rollNo:'',
    studentName: '',
    departmentName: '', // Corrected departmentName property name
    feesType: '',
    duration: '',
    collectionDate: '',
    paymentType: '',
    invoiceNumber: '',
    status: '',
    amount: 0,// Assuming the initial value for the amount is 0
    details:''
  };
  
  constructor(private feeservise:feeService, private router: Router) {}

   ngOnInit(): void {
    
  }

  close(){
    this.router.navigate(['/home/fees/all']);
  }



  onSubmit(myForm: NgForm):void{
    if(myForm.valid){
    const data: Fee = {

      id: 0,
      rollNo: this.fee.rollNo,
      studentName: this.fee.studentName,
      departmentName: this.fee.departmentName,
      feesType: this.fee.feesType,
      duration: this.fee.duration,
      collectionDate: this.fee.collectionDate,
      paymentType: this.fee.paymentType,
      invoiceNumber: this.fee.invoiceNumber,
      status: this.fee.status,
      amount: this.fee.amount,
      details: this.fee.details,
    };
    console.log(data);
    this.feeservise.addFee(data).subscribe({
      next: (res) => {
        console.log(res);
        swal({
          icon: 'success',
          title: 'Success',
          text: 'Fee has been added successfully!',
        }).then(() => {
         this.router.navigate(['/home/fees/all']);
        });
      
      },
      error: (e) => {
        console.error(e);
        swal({
          icon: 'error',
          title: 'Error',
          text: 'An error occurred while saving the data.',
        });
        
       
      }
    });
  }else{
    swal({
      icon: 'error',
      title: 'Error',
      text: 'Kindly fill all the fields.',
    });
  }
   
  }

}
