package com.app.college.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.models.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

	@Query(value = "SELECT * FROM holiday WHERE (LOWER(title) LIKE CONCAT('%', LOWER(:search), '%') OR title IS NULL) OR (LOWER(holiday_type) LIKE CONCAT('%', LOWER(:search), '%') OR holiday_type IS NULL)",
	        countQuery = "SELECT COUNT(*) FROM holiday WHERE (LOWER(title) LIKE CONCAT('%', LOWER(:search), '%') OR title IS NULL) OR (LOWER(holiday_type) LIKE CONCAT('%', LOWER(:search), '%') OR holiday_type IS NULL)",
	        nativeQuery = true)
	Page<Holiday> getHolidayBySearch(@Param("search") String search, Pageable pageable);
	
    @Query("SELECT h FROM Holiday h WHERE :date BETWEEN h.startDate AND h.endDate")
    List<Holiday> findHolidaysContainingDate(LocalDate date);

}
