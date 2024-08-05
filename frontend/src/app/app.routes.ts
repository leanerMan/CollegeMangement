import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { AllTeachersComponent } from './modules/teachers/all-teachers/all-teachers.component';
import { AddTeacherComponent } from './modules/teachers/add-teacher/add-teacher.component';
import { AboutTeacherComponent } from './modules/teachers/about-teacher/about-teacher.component';
import { EditTeacherComponent } from './modules/teachers/edit-teacher/edit-teacher.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { AllLibraryAssetComponent } from './modules/library/all-library-asset/all-library-asset.component';
import { AddLibraryAssetComponent } from './modules/library/add-library-asset/add-library-asset.component';
import { EditLibraryAssetComponent } from './modules/library/edit-library-asset/edit-library-asset.component';
import { AllTimeTableComponent } from './modules/timeTable/all-time-table/all-time-table.component';
import { AddTimeTableComponent } from './modules/timeTable/add-time-table/add-time-table.component';
import { EditTimeTableComponent } from './modules/timeTable/edit-time-table/edit-time-table.component';
import { AllStudentComponent } from './modules/students/all-student/all-student.component';
import { AddStudentComponent } from './modules/students/add-student/add-student.component';
import { AboutStudentComponent } from './modules/students/about-student/about-student.component';
import { EditStudentComponent } from './modules/students/edit-student/edit-student.component';
import { AddHoliDayComponent } from './modules/holiday/add-holi-day/add-holi-day.component';
import { AllHoliDayComponent } from './modules/holiday/all-holi-day/all-holi-day.component';
import { EditHoliDayComponent } from './modules/holiday/edit-holi-day/edit-holi-day.component';
import { AllCoursesComponent } from './modules/courses/all-courses/all-courses.component';
import { AddCoursesComponent } from './modules/courses/add-course/add-courses.component';
import { AboutCourseComponent } from './modules/courses/about-course/about-course.component';
import { EditCourseComponent } from './modules/courses/edit-course/edit-course.component';
import { AllDepartmentsComponent } from './modules/departments/all-departments/all-departments.component';
import { AddDepartmentComponent } from './modules/departments/add-department/add-department.component';
import { EditDepartmentComponent } from './modules/departments/edit-department/edit-department.component';
import { authGuard } from './guard/auth.guard';
import { AllContactComponent } from './modules/contacts/all-contact/all-contact.component';
import { AddContactComponent } from './modules/contacts/add-contact/add-contact.component';
import { AboutContactComponent } from './modules/contacts/about-contact/about-contact.component';
import { EditContactComponent } from './modules/contacts/edit-contact/edit-contact.component';
import { AllFeesComponent } from './modules/fees/all-fees/all-fees.component';
import { AddFeeComponent } from './modules/fees/add-fee/add-fee.component';
import { AboutFeeComponent } from './modules/fees/about-fee/about-fee.component';
import { EditFeeComponent } from './modules/fees/edit-fee/edit-fee.component';
import { AllStaffsComponent } from './modules/staffs/all-staffs/all-staffs.component';
import { AddstaffComponent } from './modules/staffs/add-staff/add-staff.component';
import { AboutStaffComponent } from './modules/staffs/about-staff/about-staff.component';
import { EditStaffComponent } from './modules/staffs/edit-staff/edit-staff.component';
import { StudentAttendanceComponent } from './modules/students/student-attendance/student-attendance.component';
// import { adminGuard } from './guard/admin.guard';




export const routes: Routes = [
    { path: 'login', component: LoginLayoutComponent },
    {
        path: 'admin', component: MainLayoutComponent, data: { breadcrumb: 'Home' }, canActivate: [authGuard], children: [
            { path: 'dashboard', component: DashboardComponent, title: "Dashboard", data: { breadcrumb: 'Dashboard' } },
            
            {
                path: 'teachers', data: { breadcrumb: 'Teacher' }, children: [
                    { path: 'all', component: AllTeachersComponent, title: "All Teachers", data: { breadcrumb: 'All' } },
                    { path: 'add', component: AddTeacherComponent, title: "Add Teacher", data: { breadcrumb: 'Add' } },
                    { path: 'about', component: AboutTeacherComponent, title: "About Teacher", data: { breadcrumb: 'About' } },
                    { path: 'edit', component: EditTeacherComponent, title: "Edit Teacher", data: { breadcrumb: 'Edit' } },
                    { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' } }]
            },

            {
                path: 'students', data: { breadcrumb: 'Student' }, children: [
                    { path: 'all', component: AllStudentComponent, title: "All Student", data: { breadcrumb: 'All' } },
                    { path: 'add', component: AddStudentComponent, title: "Add Student", data: { breadcrumb: 'Add' } },
                    { path: 'about', component: AboutStudentComponent, title: "About Student", data: { breadcrumb: 'About' } },
                    { path: 'edit', component: EditStudentComponent, title: "Edit Student", data: { breadcrumb: 'Edit' } },
                    { path: 'attendance', component: StudentAttendanceComponent, title: "Student Attendance", data: { breadcrumb: 'Attendance' } },
                    { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
                ]
            },

            {
                path: 'library', data: { breadcrumb: 'Library' }, children: [
                    { path: 'all', component: AllLibraryAssetComponent, title: "All Library", data: { breadcrumb: 'All' } },
                    { path: 'add', component: AddLibraryAssetComponent, title: "Add Library", data: { breadcrumb: 'Add' } },
                    { path: 'edit', component: EditLibraryAssetComponent, title: "Edit Library", data: { breadcrumb: 'Edit' } },
                    { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
                ]
            },

            {
                path: 'time-tables', data: { breadcrumb: 'Time-Table' }, children: [
                    { path: 'all', component: AllTimeTableComponent, title: "All Time Table", data: { breadcrumb: 'All' } },
                    { path: 'add', component: AddTimeTableComponent, title: "Add Time Table", data: { breadcrumb: 'Add' } },
                    { path: 'edit', component: EditTimeTableComponent, title: "Edit Time Table", data: { breadcrumb: 'Edit' } },
                    { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
                ]
            },

            { path: 'holi-day', data: { breadcrumb: 'Holiday' } ,children:[
                { path: 'add', component: AddHoliDayComponent, data: { breadcrumb: 'Add' } },
                { path: 'all', component: AllHoliDayComponent, data: { breadcrumb: 'All' } },
                { path: 'edit', component: EditHoliDayComponent, data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ]},

            { path: 'courses', data: { breadcrumb: 'Course' } ,children:[
                { path: 'all', component: AllCoursesComponent, data: { breadcrumb: 'All' } },
                { path: 'add', component: AddCoursesComponent, data: { breadcrumb: 'Add' } },
                { path: 'about', component: AboutCourseComponent, data: { breadcrumb: 'About' } },
                { path: 'edit', component: EditCourseComponent, data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ]},

            { path: 'department', data: { breadcrumb: 'Department' } ,children:[
                { path: 'all', component: AllDepartmentsComponent, data: { breadcrumb: 'All' } },
                { path: 'add', component: AddDepartmentComponent, data: { breadcrumb: 'Add' } },
                { path: 'edit', component: EditDepartmentComponent, data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ]},

            { path: 'contacts', data: { breadcrumb: 'Contact' },children:[
                { path: 'all', component: AllContactComponent, title: "All Contacts", data: { breadcrumb: 'All' } },
                { path: 'add', component: AddContactComponent, title: "Add Contact", data: { breadcrumb: 'Add' } },
                { path: 'about', component: AboutContactComponent, title: "About Contact", data: { breadcrumb: 'About' } },
                { path: 'edit', component: EditContactComponent, title: "Edit Contact", data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ] },

            { path: 'fees', data: { breadcrumb: 'Fee' },children:[
                { path: 'all', component: AllFeesComponent, title: "All Fees", data: { breadcrumb: 'All' } },
                { path: 'add', component: AddFeeComponent, title: "Add Fee", data: { breadcrumb: 'Add' } },
                { path: 'about', component: AboutFeeComponent, title: "About Fee", data: { breadcrumb: 'About' } },
                { path: 'edit', component: EditFeeComponent, title: "Edit Fee", data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ] },

            { path: 'staffs', data: { breadcrumb: 'Staff' },children:[
                { path: 'all', component: AllStaffsComponent, data: { breadcrumb: 'All' } },
                { path: 'add', component: AddstaffComponent, data: { breadcrumb: 'Add' } },
                { path: 'about', component: AboutStaffComponent, data: { breadcrumb: 'About' } },
                { path: 'edit', component: EditStaffComponent, data: { breadcrumb: 'Edit' } },
                { path: '', redirectTo: 'all', pathMatch: 'full', data: { breadcrumb: 'All' }}
            ] },

            { path: '', redirectTo: 'dashboard', pathMatch: 'full' },


        ]
    },
    { path: '', redirectTo: 'admin', pathMatch: 'full' }
];
