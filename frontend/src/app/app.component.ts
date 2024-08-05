import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StorageService } from './core/storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'CollegeAdmin';

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;


  constructor(
    private storageService: StorageService
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('admin');
      this.showModeratorBoard = this.roles.includes('mod');

      this.username = user.username;
    }

  }

}
