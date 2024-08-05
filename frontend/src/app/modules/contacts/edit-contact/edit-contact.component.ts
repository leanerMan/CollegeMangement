import { Component } from '@angular/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MY_FORMATS } from '../../../_helper/MyFormat';
import { Contact } from '../../../models/contact';
import { Router } from '@angular/router';
import { ContactService } from '../../../core/service/contact.service';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';

@Component({
  selector: 'app-edit-contact',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule],
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './edit-contact.component.html',
  styleUrl: './edit-contact.component.css'
})
export class EditContactComponent {

  selectedId:string | null | undefined 
  selectedcontact: Contact | undefined;
  submitted = false;

  file: File | null = null;
  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;


  constructor(private router: Router, private contactService: ContactService) { }

  ngOnInit() {
    this.selectedId = this.contactService.getSelectedItemId();
    if (this.selectedId==null || this.selectedId==undefined) {
      this.router.navigate(['admin','contacts','all'])
    } else {
      this.contactService.getcontactById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedcontact = res.data
          if(this.selectedcontact){
            console.log(this.selectedcontact.contactImageName);
            if (this.selectedcontact.contactImageName != undefined && this.selectedcontact.contactImageName != null && this.selectedcontact.contactImageName != "") {
              this.imageSrc = 'https://cargofile.s3.ap-south-1.amazonaws.com/contacts/' + this.selectedcontact.contactImageName;
            } else {
              this.imageSrc = this.defaultImage;
            }
      
          }

        },
        error: (e) => console.error(e)
      });

 
    }
 

   
  }
  onDateReturn(event: any): any {
    // Convert Moment object to JavaScript Date object
    return event.format('YYYY-MM-DD');
  }

  savecontact(): void {
    if (this.selectedcontact) {
    this.submitted = true;
    console.log(this.selectedcontact);
    // this.selectedcontact.joiningDate = this.selectedcontact.joiningDate.toISOString().slice(0, 10);

    const formData = new FormData();
    this.selectedcontact.birthDate= (typeof this.selectedcontact.birthDate === 'string') ?  this.selectedcontact.birthDate : this.onDateReturn(this.selectedcontact.birthDate),
      // Append form data to FormData object
      Object.entries(this.selectedcontact).forEach(([key, value]) => {
        formData.append(key, value);
      });

      if (this.file) {
        formData.append('contactImageFile', this.file)
      }
 



    // this.contact.joiningDate=this.contact.joiningDate?.toLocaleDateString();
    this.contactService.updatecontact(formData,this.selectedId)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          alert(res.message);
          this.router.navigate(['admin', 'contacts', 'all']);
        },
        error: (e) => {
          console.error(e)
          alert(e.error.message)
        }
      });
    }
  }


  onFileChange(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const files: FileList = event.target.files;
      if (files.length > 0) {
        this.file = files[0];
      }
      const reader = new FileReader();
      reader.onload = () => {
        this.imageSrc = reader.result as string;
        this.imageUploaded = true;
      }
      reader.readAsDataURL(files[0]);
    }
  }

  removeImage(): void {
    if(this.selectedcontact){
      this.imageSrc = 'https://cargofile.s3.ap-south-1.amazonaws.com/contacts/' + this.selectedcontact.contactImageName;
      this.imageUploaded = false;
      // Clear the file input
      const input = document.getElementById('imag') as HTMLInputElement;
      if (input) {
        input.value = '';
      }
    }
    }

}
