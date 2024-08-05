package com.app.college.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.college.DTO.StuAdmidCardUgPgView;
import com.app.college.DTO.StuExamView;
import com.app.college.DTO.StuResultView;
import com.app.college.models.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	@Query(value="select * from stu_admid_card_ug_pg WHERE student_id=:studentId", nativeQuery = true)
	List<StuAdmidCardUgPgView> getStuAdmidCardUgPgById(String studentId);

	@Query(value="select * from stu_result WHERE rollno=:rollno", nativeQuery = true)
	List<StuResultView> getStuResultById(String rollno);

	@Query(value="select * from stu_exam WHERE  rollno=:rollno", nativeQuery = true)
	List<StuExamView> getStuExamById(String rollno);

	@Query(value="SELECT * FROM notice WHERE (:categoryId IS NULL OR category_id = :categoryId) AND (:departmentId IS NULL OR department_id = :departmentId)", nativeQuery = true)
	List<Notice> getAllNotice(Long categoryId, Long departmentId);

	@Query(value="SELECT * FROM notice WHERE (:categoryId IS NULL OR category_id = :categoryId) AND (:departmentId IS NULL OR department_id = :departmentId)", nativeQuery = true)
	Page<Notice> getAllNotice(Pageable pageable, Long categoryId, Long departmentId);

	@Query(value="select * from stu_admid_card_ug_pg WHERE student_id=:studentId AND sem=:sem", nativeQuery = true)
	StuAdmidCardUgPgView getStuAdmidCardUgPgByIdAndSem(String studentId, String sem);
	
	@Query(value="select * from stu_result WHERE rollno=:rollno AND semyr_code=:sem", nativeQuery = true)
	StuResultView getStuResultByIdAndSem(String rollno, String sem);

}
