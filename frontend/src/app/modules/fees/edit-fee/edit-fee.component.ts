import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { feeService } from '../../../core/service/fee.service';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert';
import { Fee } from '../../../models/fee';


@Component({
  selector: 'app-edit-fee',
  standalone: true,
  imports: [CommonModule,FormsModule,MatRadioModule],
  templateUrl: './edit-fee.component.html',
  styleUrl: './edit-fee.component.css'
})
export class EditFeeComponent {
  fee: any = {};
  id!: number; 
  constructor(private feeservise:feeService,private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.EditfeeComponent();
  }



  EditfeeComponent(){
    this.route.params.subscribe(params => {
      // Access the id parameter from the route
      this.id = params['id'];

      console.log("ID:", this.id);
    });
    if (this.id !== undefined) {
    this.feeservise.getfee(this.id)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.fee = res.data;  
      

      },
      error: (e) => console.error(e)
    });
  }
}


     onSubmit(myForm: NgForm):void{
      if(myForm.valid){
        const data: Fee = {
    
          id: this.fee.id,
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
        this.feeservise.updatefee(data).subscribe({
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


    close(){
      this.router.navigate(['/home/fees/all']);
    }
}
