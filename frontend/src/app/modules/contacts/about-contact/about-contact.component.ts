import { Component } from '@angular/core';
import { Contact } from '../../../models/contact';
import { Router } from '@angular/router';
import { ContactService } from '../../../core/service/contact.service';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [],
  templateUrl: './about-contact.component.html',
  styleUrl: './about.component.css'
})
export class AboutContactComponent {

  selectedId:string | null | undefined 
  selectedcontact: Contact | undefined;
  constructor(private route: Router, private contactService: ContactService) { }
  profilePhoto='';

  ngOnInit() {
    this.selectedId = this.contactService.getSelectedItemId();
    if (this.selectedId==null || this.selectedId==undefined) {
      this.route.navigate(['admin','contacts','all'])
    } else {
      this.contactService.getcontactById(this.selectedId).subscribe({
        next: (res) => {
          console.log(res.data);
          this.selectedcontact = res.data
          // this.length = res.data.totalElements;

        },
        error: (e) => console.error(e)
      });
    }
    if(this.selectedcontact){
      this.profilePhoto=this.selectedcontact.contactImageName;
    }

  }

}
