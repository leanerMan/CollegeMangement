import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { Router, RouterModule } from '@angular/router';
import { NgClass, NgStyle } from '@angular/common';
import { StorageService } from '../../core/storage.service';
import { BreadcrumbComponent } from '../../shared/breadcrumb/breadcrumb.component';


@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent, RouterModule,NgClass,NgStyle,BreadcrumbComponent],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent implements OnInit{

  constructor(private storageService :StorageService,private router:Router){}

  isLoggedIn = false;
  role='';

  ngOnInit(): void {
 
  }

  isButtonToggled: boolean = false;

  onToggleButtonClicked(isToggled: boolean) {
    this.isButtonToggled = !this.isButtonToggled;
  }


  

}
