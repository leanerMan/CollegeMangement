package com.app.college.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.DTO.TeacherDtoRequest;
import com.app.college.models.StaffAttendance;
import com.app.college.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//	@Query("SELECT t FROM Teacher t WHERE (t.firstName LIKE %:keyword% " + "OR t.lastName LIKE %:keyword% " + "OR t.email LIKE %:keyword%)")
//	Page<Teacher> searchTeachers(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT t FROM Teacher t WHERE LOWER(t.firstName) LIKE %:keyword% OR LOWER(t.lastName) LIKE %:keyword% OR LOWER(t.email) LIKE %:keyword%")
	Page<Teacher> searchTeachers(@Param("keyword") String keyword, Pageable pageable);

	Boolean existsByMobile(String contactPhone);

	Optional<Teacher> findByMobile(String contactPhone);

	Optional<Teacher> findByUsername(String username);

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM teachers WHERE username = ?1", nativeQuery = true)
	boolean exitsByUsername(String username);

	@Query("select attendance from Teacher t where t.id=:id")
	List<StaffAttendance> findAllAttendanceById(Long id);

}
