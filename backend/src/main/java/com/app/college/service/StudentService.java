package com.app.college.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.DTO.StudentDtoRequest;
import com.app.college.models.Student;
import com.app.college.payload.response.MonthAttendance;

import jakarta.validation.Valid;

public interface StudentService {


	StudentDtoRequest updateStudentById(Long id,@Valid StudentDtoRequest studentsDtoReq,MultipartFile profilePhotoFile,MultipartFile profileSignatureFile);

	String deleteStudentById(Long id);

	
	StudentDtoRequest getStudentById(Long id);

	List<StudentDtoRequest> getAllStudent();

	StudentDtoRequest createStudentsWithProfilePhoto(@Valid StudentDtoRequest studentsDtoReq, MultipartFile profilePhotoFile, MultipartFile profileSignatureFile);

	Page<StudentDtoRequest> getAllStudent(Pageable pageable);

	Page<Student> searchStudents(Pageable pageable, String keyword);

	List<StudentAttendanceDtoRequest> getAttendaceByStudentId(Long studentId);

	StudentDtoRequest getStudentByUsername(String username);

	Object getAllStudentAttendance(Pageable pageable,YearMonth yearMonth);

}
