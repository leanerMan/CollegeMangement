import { SidebarItem } from "./sidebar-item";

export const sidebarData: SidebarItem[] =  [
    {
      label: 'Dashboard',
      icon: 'fa fa-table-columns',
      link: 'dashboard',
      enable:true,
    },
    {
      label: 'Teacher',
      icon: 'fa fa-chalkboard-user',
      link: 'teachers/all',
      enable:true ,
      subItems: [
        { label: 'All Teacher', icon: 'person', link: 'teachers/all',enable:true },
        { label: 'Add Teacher', icon: 'person', link: 'teachers/add',enable:true },
        { label: 'Edit Teacher', icon: 'person', link: 'teachers/edit',enable:false },
        { label: 'About Teacher', icon: 'person', link: 'teachers/about',enable:false }
      ]
    },
    {
        label: 'student',
        icon: 'fa fa-user-graduate',
        link: 'students/all',
        enable:true,
        subItems: [
          { label: 'All student', icon: 'person', link: 'students/all',enable:true },
          { label: 'Add student', icon: 'person', link: 'students/add',enable:true },
          { label: 'Edit student', icon: 'person', link: 'students/edit',enable:false },
          { label: 'About student', icon: 'person', link: 'students/about',enable:false },
          { label: 'Student Attendance', icon: 'person', link: 'students/attendance',enable:true }
        ]
      },
      {
        label: 'course',
        icon: 'fa fa-graduation-cap',
        link: 'courses/all',
        enable:true,
        subItems: [
          { label: 'All course', icon: 'person', link: 'courses/all',enable:true },
          { label: 'Add course', icon: 'person', link: 'courses/add',enable:true },
          { label: 'Edit course', icon: 'person', link: 'courses/edit/:id',enable:false },
          { label: 'About course', icon: 'person', link: 'courses/about/:id',enable:false }
        ]
      },
      {
        label: 'time-table',
        icon: 'fa fa-calendar-week',
        link: 'time-tables/all',
        enable:true,
        subItems: [
          { label: 'All time-table', icon: 'person', link: 'time-tables/all' ,enable:true },
          { label: 'Add time-table', icon: 'person', link: 'time-tables/add' ,enable:true },
          { label: 'Edit time-table', icon: 'person', link: 'time-tables/edit/:id',enable:false  }
        ]
      },
	        {
        label: 'holiday',
        icon: 'fa fa-snowflake',
        link: 'holi-day/all',
        enable:true,
        subItems: [
          { label: 'All holi-day', icon: 'person', link: 'holi-day/all' ,enable:true },
          { label: 'Add holi-day', icon: 'person', link: 'holi-day/add' ,enable:true },
          { label: 'Edit holi-day', icon: 'person', link: 'holi-day/edit/:id',enable:false  }
        
        ]
      },
      {
        label: 'library',
        icon: 'fa fa-book-open-reader',
        link: 'library/all',
        enable:true ,
        subItems: [
          { label: 'All library', icon: 'person', link: 'library/all' ,enable:true },
          { label: 'Add library', icon: 'person', link: 'library/add' ,enable:true },
          { label: 'Edit library', icon: 'person', link: 'library/edit' ,enable:false },
          { label: 'About library', icon: 'person', link: 'library/about',enable:false  }
        ]
      },
      {
        label: 'department',
        icon: 'fa fa-building',
        link: 'department/all',
        enable:true ,
        subItems: [
          { label: 'all department', icon: 'person', link: 'department/all',enable:true  },
          { label: 'Add department', icon: 'person', link: 'department/add',enable:true  },
          { label: 'Edit department', icon: 'person', link: 'department/edit/:id' ,enable:false }
        ]
      },
      {
        label: 'staff',
        icon: 'fa fa-person',
        link: 'staffs/all',
        enable:true ,
        subItems: [
          { label: 'All staff', icon: 'person', link: 'staffs/all',enable:true  },
          { label: 'Add staff', icon: 'person', link: 'staffs/add',enable:true  },
          { label: 'Edit staff', icon: 'person', link: 'staffs/edit',enable:false  },
          { label: 'About staff', icon: 'person', link: 'staffs/about' ,enable:false }
        ]
      },
      {
        label: 'contact',
        icon: 'fa fa-address-book',
        link: 'contacts/all',
        enable:true ,
        subItems: [
          { label: 'All contact', icon: 'person', link: 'contacts/all' ,enable:true },
          { label: 'Add contact', icon: 'person', link: 'contacts/add' ,enable:true },
          { label: 'Edit contact', icon: 'person', link: 'contacts/edit',enable:false  },
          { label: 'About contact', icon: 'person', link: 'contacts/about',enable:false  }
        ]
      },
      {
        label: 'fee',
        icon: 'fa fa-sack-dollar',
        link: 'fees/all',
        enable:true ,
        subItems: [
          { label: 'All fee', icon: 'person', link: 'fees/all',enable:true  },
          { label: 'Add fee', icon: 'person', link: 'fees/add' ,enable:true },
          { label: 'Edit fee', icon: 'person', link: 'fees/edit',enable:false  },
          { label: 'About fee', icon: 'person', link: 'fees/about' ,enable:false }
        ]
      }
    
  ];


 
  