package com.app.college.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	@Query(value = "SELECT * FROM staffs WHERE (LOWER(address) LIKE CONCAT('%', LOWER(:search), '%') OR address IS NULL) OR (LOWER(designation) LIKE CONCAT('%', LOWER(:search), '%') OR designation IS NULL) OR (LOWER(education) LIKE CONCAT('%', LOWER(:search), '%') OR education IS NULL) OR (LOWER(departments) LIKE CONCAT('%', LOWER(:search), '%') OR departments IS NULL) OR (LOWER(gender) LIKE CONCAT('%', LOWER(:search), '%') OR gender IS NULL) OR (LOWER(last_name) LIKE CONCAT('%', LOWER(:search), '%') OR last_name IS NULL) OR (LOWER(mobile) LIKE CONCAT('%', LOWER(:search), '%') OR mobile IS NULL) OR (LOWER(email) LIKE CONCAT('%', LOWER(:search), '%') OR email IS NULL) OR (LOWER(first_name) LIKE CONCAT('%', LOWER(:search), '%') OR first_name IS NULL)",
	        nativeQuery = true, countQuery = "SELECT COUNT(*) FROM staffs WHERE (LOWER(address) LIKE CONCAT('%', LOWER(:search), '%') OR address IS NULL) OR (LOWER(designation) LIKE CONCAT('%', LOWER(:search), '%') OR designation IS NULL) OR (LOWER(education) LIKE CONCAT('%', LOWER(:search), '%') OR education IS NULL) OR (LOWER(departments) LIKE CONCAT('%', LOWER(:search), '%') OR departments IS NULL) OR (LOWER(gender) LIKE CONCAT('%', LOWER(:search), '%') OR gender IS NULL) OR (LOWER(last_name) LIKE CONCAT('%', LOWER(:search), '%') OR last_name IS NULL) OR (LOWER(mobile) LIKE CONCAT('%', LOWER(:search), '%') OR mobile IS NULL) OR (LOWER(email) LIKE CONCAT('%', LOWER(:search), '%') OR email IS NULL) OR (LOWER(first_name) LIKE CONCAT('%', LOWER(:search), '%') OR first_name IS NULL)")
	Page<Staff> getStaffBySearch(@Param("search") String search, Pageable pageable);

}
