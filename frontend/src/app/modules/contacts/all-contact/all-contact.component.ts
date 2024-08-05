import { Component } from '@angular/core';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { ContactService } from '../../../core/service/contact.service';
import swal from 'sweetalert';
import { Contact } from '../../../models/contact';
import { DatePipe } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-all-contact',
  standalone: true,
  imports: [MatPaginatorModule, MatTooltipModule, DatePipe,FormsModule],
  templateUrl: './all-contact.component.html',
  styleUrl: './all-contact.component.css'
})
export class AllContactComponent {


  
    contacts: Contact[] = [];
    currentcontact: any;
    currentIndex = -1;
    keyword = "";
  
    // currentPage: number = 0;
    pageSize: number = 5;
    length: number = 0;
    pageIndex = 0;
    pageSizeOptions = [5, 10, 25];
  
    hidePageSize = false;
    showPageSizeOptions = true;
    showFirstLastButtons = true;
    disabled = false;
  
  
    pageEvent: PageEvent | undefined;
  
    constructor(private router: Router, private contactservice: ContactService) { }
  
    ngOnInit(): void {
      this.retrievecontacts(this.pageIndex, this.pageSize,this.keyword);
    }
  
  
    handlePageEvent(e: PageEvent) {
      this.pageEvent = e;
      this.length = e.length;
      this.pageSize = e.pageSize;
      this.pageIndex = e.pageIndex;
      this.retrievecontacts(this.pageIndex, this.pageSize,this.keyword);
    }
  
    setPageSizeOptions(setPageSizeOptionsInput: string) {
      if (setPageSizeOptionsInput) {
        this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
      }
    }
  
    retrievecontacts(pageIndex: number, pageSize: number,keyword:string): void {
      // swal("Good job!", "You clicked the button!", "success");
      this.contactservice.getcontacts(pageIndex, pageSize,keyword)
        .subscribe({
          next: (res) => {
            console.log(res.data);
            this.contacts = res.data.content;
            this.length = res.data.totalElements;
  
          },
          error: (e) => console.error(e)
        });
    }
    addcontact() {
      this.router.navigate(['admin', 'contacts', 'add']);
    }

  
    editcontact(id: string) {
      this.contactservice.setSelectedItemId(id);
      this.router.navigate(['admin', 'contacts', 'edit']);
    }
  
    aboutcontact(id: string) {
      this.contactservice.setSelectedItemId(id);
      this.router.navigate(['admin', 'contacts', 'about']);
    }
  
    refreshList(): void {
      this.retrievecontacts(this.pageIndex, this.pageSize,this.keyword);
      this.currentcontact = {};
      this.currentIndex = -1;
    }
  
    removecontact(contact: Contact): void {
      swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover!",
        icon: "warning",
        buttons: ['cancel', 'yes'],
        dangerMode: true
      })
        .then((willDelete) => {
          if (willDelete) {
            if (contact.id)
              this.contactservice.deletecontact(contact.id)
                .subscribe({
                  next: (res) => {
                    console.log(res);
                    this.refreshList();
                  },
                  error: (e) => console.error(e)
                });
            swal("contact has been deleted!", {
              icon: "success",
            });
          }
        });
    }
  
    search(): void {
      console.log("data search",this.keyword)
      this.pageIndex = 0;
      this.retrievecontacts(this.pageIndex, this.pageSize,this.keyword);
  
    }

}
