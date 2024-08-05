import { Component, inject } from '@angular/core';
import { AuthService } from '../../core/auth.service';
import { StorageService } from '../../core/storage.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgClass } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-layout',
  standalone: true,
  imports: [FormsModule,HttpClientModule,NgClass],
  templateUrl: './login-layout.component.html',
  styleUrl: './login-layout.component.css'
})
export class LoginLayoutComponent {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private authService=inject(AuthService);
  private storageService=inject(StorageService);
  private router=inject(Router);
  constructor(  ) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      console.log(this.storageService.getUser());
      this.roles = this.storageService.getUser().roles;
      this.roles = this.storageService.getUser().roles;

      // Define the priority order of roles
      const rolePriority = ['admin', 'user']; // Reversed role priorities
      
      // Find the highest priority role the user has
      const highestPriorityRole = rolePriority.find(role => this.roles.includes(role));
      
      // Navigate based on the highest priority role
      if (highestPriorityRole) {
        switch (highestPriorityRole) {
          case 'admin':
            this.router.navigate(['admin']);
            break;
          case 'user':
            this.router.navigate(['user']);
            break;
          default:
            this.isLoggedIn = false;
            this.logout();
            this.reloadPage();
            break;
        }
      } else {
        this.isLoggedIn = false;
        this.logout();
        this.reloadPage();
        // Handle cases where user doesn't have any of the expected roles
      }
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password,'WEB').subscribe({
      next: data => {
        console.log(data);
        this.storageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        // this.authService.setJWTToken(data.token);
        // this.role = this.storageService.getUser().role;
        this.reloadPage();
      },
      error: err => {
        console.log(err);
        // this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }

  logout(): void {
    this.authService.logout()
  }

}
