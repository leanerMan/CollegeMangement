package com.app.college.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

	@Query(value = "SELECT\r\n"
			+ "    (SELECT COUNT(*) FROM students WHERE EXTRACT(YEAR FROM admission_date) < EXTRACT(YEAR FROM CURRENT_DATE)) AS totalOldStudents,\r\n"
			+ "    (SELECT COUNT(*) FROM students WHERE EXTRACT(YEAR FROM admission_date) = EXTRACT(YEAR FROM CURRENT_DATE)) AS totalNewStudents,\r\n"
			+ "    (SELECT COUNT(*) FROM courses WHERE EXTRACT(YEAR FROM start_date) < EXTRACT(YEAR FROM CURRENT_DATE)) AS totalOldCourses,\r\n"
			+ "    (SELECT COUNT(*) FROM courses WHERE EXTRACT(YEAR FROM start_date) = EXTRACT(YEAR FROM CURRENT_DATE)) AS totalNewCourses,\r\n"
			+ "    (SELECT COUNT(*) FROM teachers WHERE EXTRACT(YEAR FROM joining_date) < EXTRACT(YEAR FROM CURRENT_DATE)) AS totalOldTeachers,\r\n"
			+ "    (SELECT COUNT(*) FROM teachers WHERE EXTRACT(YEAR FROM joining_date) = EXTRACT(YEAR FROM CURRENT_DATE)) AS totalNewTeachers,\r\n"
			+ "    (SELECT COUNT(*) FROM departments WHERE EXTRACT(YEAR FROM department_start_date) < EXTRACT(YEAR FROM CURRENT_DATE)) AS totalOldDepartments,\r\n"
			+ "    (SELECT COUNT(*) FROM departments WHERE EXTRACT(YEAR FROM department_start_date) = EXTRACT(YEAR FROM CURRENT_DATE)) AS totalNewDepartments", nativeQuery = true)
	Long[][] getMisDashboardData();

	@Query(value = "SELECT * FROM library WHERE (LOWER(asset_type) LIKE CONCAT('%', LOWER(:search), '%') OR asset_type IS NULL) OR (LOWER(bill_no) LIKE CONCAT('%', LOWER(:search), '%') OR bill_no IS NULL)  OR (LOWER(department) LIKE CONCAT('%', LOWER(:search), '%') OR department IS NULL) "
	        + "   OR (LOWER(publisher) LIKE CONCAT('%', LOWER(:search), '%') OR publisher IS NULL)  OR (LOWER(subject) LIKE CONCAT('%', LOWER(:search), '%') OR subject IS NULL) OR (LOWER(status) LIKE CONCAT('%', LOWER(:search), '%') OR status IS NULL) ",
	        nativeQuery = true, countQuery = "SELECT COUNT(*) FROM library WHERE (LOWER(asset_type) LIKE CONCAT('%', LOWER(:search), '%') OR asset_type IS NULL) OR (LOWER(bill_no) LIKE CONCAT('%', LOWER(:search), '%') OR bill_no IS NULL)  OR (LOWER(department) LIKE CONCAT('%', LOWER(:search), '%') OR department IS NULL) "
	                + "   OR (LOWER(publisher) LIKE CONCAT('%', LOWER(:search), '%') OR publisher IS NULL)  OR (LOWER(subject) LIKE CONCAT('%', LOWER(:search), '%') OR subject IS NULL) OR (LOWER(status) LIKE CONCAT('%', LOWER(:search), '%') OR status IS NULL) ")
	Page<Library> getLibraryBySearch(@Param("search") String search, Pageable pageable);


}
