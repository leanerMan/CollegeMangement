package com.app.college.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.college.models.Student;
import com.app.college.models.StudentAttendance;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
	
	@Query("select distinct a.attendanceDate from StudentAttendance a where a.student=:student and a.attendanceDate between :firstDayOfMonth and :lastDayOfMonth")
	List<LocalDate> findAttendanceDateByStudentAndAttendanceDateBetween(Student student,LocalDate firstDayOfMonth,LocalDate lastDayOfMonth);

}
