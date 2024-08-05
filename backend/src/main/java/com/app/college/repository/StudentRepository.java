package com.app.college.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.college.models.Student;
import com.app.college.models.StudentAttendance;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE %:keyword% OR LOWER(s.email) LIKE %:keyword%")
	Page<Student> searchStudents(Pageable pageable, String keyword);

	@Query("select attendance from Student s where s.id=:studentId")
	List<StudentAttendance> findAllAttendanceById(Long studentId);

	Optional<Student> findByMobile(String contactPhone);

	Boolean existsByMobile(String contactPhone);


	Optional<Student> findByUsername(String username);
	
	 @Query("SELECT s FROM Student s JOIN s.attendance a WHERE FUNCTION('YEAR', a.attendanceDate) = :year AND FUNCTION('MONTH', a.attendanceDate) = :month")
	Page<Student> findAllByYearMonth(Pageable pageable, Integer year, Integer month);

//	 @Query(value="SELECT * "
//	 		+ "FROM students "
//	 		+ "LEFT OUTER JOIN students_attendance ON students_attendance.student_id = students.id "
//	 		+ "and students_attendance.attendance_date BETWEEN '2024-03-10' AND '2024-03-19'",nativeQuery = true)
//	Page<Student> findAllByAttendanceAttendanceDateBetween(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
//			Pageable pageable);
	 

	 
			 @Query("SELECT s"
			 		+ " FROM Student s")
		Page<Student> findAllByAttendanceAttendanceDateBetween(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
				Pageable pageable);

}
