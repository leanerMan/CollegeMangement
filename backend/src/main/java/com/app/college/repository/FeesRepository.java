package com.app.college.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Fees;

public interface FeesRepository extends JpaRepository<Fees, Long> {

	@Query(value = "SELECT * FROM fees WHERE (LOWER(roll_no) LIKE CONCAT('%', LOWER(:search), '%') OR roll_no IS NULL)  OR (LOWER(student_name) LIKE CONCAT('%', LOWER(:search), '%') OR student_name IS NULL)  OR (LOWER(department_name) LIKE CONCAT('%', LOWER(:search), '%') OR department_name IS NULL) "
	        + "   OR (LOWER(fees_type) LIKE CONCAT('%', LOWER(:search), '%') OR fees_type IS NULL)  OR (LOWER(payment_type) LIKE CONCAT('%', LOWER(:search), '%') OR payment_type IS NULL)  OR (LOWER(invoice_number) LIKE CONCAT('%', LOWER(:search), '%') OR invoice_number IS NULL) OR (LOWER(status) LIKE CONCAT('%', LOWER(:search), '%') OR status IS NULL) ",
	        nativeQuery = true, countQuery = "SELECT COUNT(*) FROM fees WHERE (LOWER(roll_no) LIKE CONCAT('%', LOWER(:search), '%') OR roll_no IS NULL)  OR (LOWER(student_name) LIKE CONCAT('%', LOWER(:search), '%') OR student_name IS NULL)  OR (LOWER(department_name) LIKE CONCAT('%', LOWER(:search), '%') OR department_name IS NULL) "
	                + "   OR (LOWER(fees_type) LIKE CONCAT('%', LOWER(:search), '%') OR fees_type IS NULL)  OR (LOWER(payment_type) LIKE CONCAT('%', LOWER(:search), '%') OR payment_type IS NULL)  OR (LOWER(invoice_number) LIKE CONCAT('%', LOWER(:search), '%') OR invoice_number IS NULL) OR (LOWER(status) LIKE CONCAT('%', LOWER(:search), '%') OR status IS NULL) ")
	Page<Fees> getFeesBySearch(@Param("search") String search, Pageable pageable);


}
