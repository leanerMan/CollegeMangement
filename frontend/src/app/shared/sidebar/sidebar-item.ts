export interface SidebarItem {
    label: string;
    icon: string;
    link?: string;
    enable:boolean;
    subItems?: SidebarItem[];
  }