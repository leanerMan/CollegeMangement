import { Component, signal } from '@angular/core';
import { RouterLink, RouterModule,Router } from '@angular/router';
import { sidebarData } from './sidebar-data';
import { NgClass, NgStyle } from '@angular/common';
import { SidebarItem } from './sidebar-item';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { DataService } from '../app.serviceShare';


@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink,RouterModule,NgClass,NgStyle],
  templateUrl: './sidebar.component.html',
  styles: [`
    .ddclick.active {
        transform: rotate(90deg);
    }

    .sidelink {
        height: 100%;
        overflow: scroll;
    }

    .extra a{
        pointer-events:none;
        background:#fff;
    }
  `],
  animations: [
    trigger('slideUpDown', [
      state('up', style({
        height:"0", display:"none",
      
        
      })),
      state('down', style({
        height: '*',
       
      })),
      transition('up <=> down', [
        animate('0.5s ease-in-out')
      ])
    ])
  ]
})
export class SidebarComponent {
  isActive: boolean = false;
  menu = sidebarData;
  subItem?: SidebarItem;
  activeItemIndex = signal(-1);
  constructor(private dataService : DataService,private router: Router){}

  toggleActive(index: number) {

    if(this.activeItemIndex()===index){
      this.activeItemIndex.set(-1); 
    }else{
      this.activeItemIndex.set(index);
    }
  }

}
