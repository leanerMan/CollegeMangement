import { Component } from '@angular/core';
import { Contact } from '../../../models/contact';
import { ContactService } from '../../../core/service/contact.service';
import { Router } from '@angular/router';
import { FormsModule, NgModel } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../../../_helper/MyFormat';


@Component({
  selector: 'app-add-contact',
  standalone: true,
  imports: [FormsModule,MatDatepickerModule,MatDatepickerModule],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  templateUrl: './add-contact.component.html',
  styleUrl: './add-contact.component.css'
})
export class AddContactComponent {

  submitted= false ;

  file:File | null = null; 

  contact: Contact={
    id: null,
  address:'',
  birthDate:null,
  contactImageName:'',
  email:'',
  mobile:'',
  name:'',
  note:''
  };

  constructor( private contactService: ContactService,private router:Router) {}

  ngOnInit(): void {
  
  }

  defaultImage: string = "assets/images/userimg.jpg";
  imageSrc: string = this.defaultImage;
  imageUploaded: boolean = false;

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
    this.imageSrc = this.defaultImage;
    this.imageUploaded = false;
    // Clear the file input
    const input = document.getElementById('imag') as HTMLInputElement;
    if (input) {
      input.value = '';
    }
  }




  savecontact(): void {
    this.submitted = true;
    console.log(this.contact);
    this.contact.birthDate=this.contact.birthDate.toISOString().slice(0, 10);

    const formData = new FormData();

    // Append form data to FormData object
    Object.entries(this.contact).forEach(([key, value]) => {
      if(key!=='id'){
        formData.append(key, value);
      }
    });

    if(this.file){
      formData.append('contactImageFile',this.file)
    }
  
    // this.contact.joiningDate=this.contact.joiningDate?.toLocaleDateString();
    this.contactService.addcontact(formData)
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
