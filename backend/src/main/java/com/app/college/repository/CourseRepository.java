package com.app.college.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query(value = "SELECT * "
	        + "FROM courses "
	        + "WHERE (LOWER(contact_number) LIKE CONCAT('%', LOWER(:search), '%') OR contact_number IS NULL) "
	        + "   OR (LOWER(course_code) LIKE CONCAT('%', LOWER(:search), '%') OR course_code IS NULL) "
	        + "   OR (LOWER(course_details) LIKE CONCAT('%', LOWER(:search), '%') OR course_details IS NULL) "
	        + "   OR (LOWER(course_name) LIKE CONCAT('%', LOWER(:search), '%') OR course_name IS NULL) "
	        + "   OR (LOWER(professor_name) LIKE CONCAT('%', LOWER(:search), '%') OR professor_name IS NULL) ",
	        nativeQuery = true)
	Page<Course> getCourseBySearch(@Param("search") String search, Pageable pageable);

	@Query(value = "SELECT * FROM courses where department_id = :depId", nativeQuery = true)
	List<Course> getAllCourseByDepartment(Long depId);

	@Query(value = "SELECT department_id FROM students WHERE username = :username UNION SELECT department_id FROM teachers WHERE username = :username", nativeQuery = true)
	Long findDepIdByUsername(String username);


}
