import { Component, Input, inject } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import { StorageService } from '../../core/storage.service';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  template: `
    <div class="tophead">
      <div class="logo aos-init aos-animate" data-aos="fade-left" data-aos-duration="1500">
        <img src="../../../assets/images/hrms-logo.png" alt="hrms">
      </div> 
      <div class="hidemenu" (click)="onToggleButtonClick()"><i class="fa fa-bars"></i></div>
    </div> 
    <div>
      <button (click)="logout()" class="userlogin" >
        Admin <span><img src="../../../assets/images/users.jpg" alt="student"></span></button>
    </div>
    `,
  styles: [`
  .topheading{
    width: 100%;}
    `]

})
export class HeaderComponent {
  @Output() toggleButtonClicked: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() isButtonToggled: boolean = false;

  private authService = inject(AuthService);

  onToggleButtonClick() {
    this.toggleButtonClicked.emit(true);
  }
  constructor(private storageService: StorageService) { }

  // logout(): void {
  //   this.authService.logout().subscribe({
  //     next: res => {
  //       console.log(res);
  //       this.storageService.clean();

  //       window.location.reload();
  //     },
  //     error: err => {
  //       console.log(err);
  //     }
  //   });
  // }

  logout() {
    this.authService.logout();
    // this.storageService.clean();
  }


}
