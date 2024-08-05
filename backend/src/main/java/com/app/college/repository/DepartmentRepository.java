package com.app.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query(value = "SELECT * "
	        + "FROM departments "
	        + "WHERE (LOWER(department) LIKE CONCAT('%', LOWER(:search), '%') OR department IS NULL) "
	        + "   OR (LOWER(email) LIKE CONCAT('%', LOWER(:search), '%') OR email IS NULL) "
	        + "   OR (LOWER(head_of_department) LIKE CONCAT('%', LOWER(:search), '%') OR head_of_department IS NULL) "
	        + "   OR (LOWER(phone) LIKE CONCAT('%', LOWER(:search), '%') OR phone IS NULL) ",
	        nativeQuery = true)
	List<Department> getDepartmentBySearch(@Param("search") String search);


}
