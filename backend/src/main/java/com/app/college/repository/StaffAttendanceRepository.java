package com.app.college.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.college.models.StaffAttendance;
import com.app.college.models.Student;
import com.app.college.models.Teacher;

public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Long> {

//	Page<StaffAttendance> findByStaffId(Long staffId, Pageable pageable);

	@Query("select distinct a.attendanceDate from StaffAttendance a where a.teacher=:teacher and a.attendanceDate between :firstDayOfMonth and :lastDayOfMonth")
	List<LocalDate> findAttendanceDateByTeacherAndAttendanceDateBetween(Teacher teacher,LocalDate firstDayOfMonth,LocalDate lastDayOfMonth);

}
