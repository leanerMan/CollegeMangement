package com.app.college.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.StudentAttendanceDtoRequest;

public interface StudentAttendanceService {

//	StudentAttendanceDtoRequest createStudentAttendace(@Valid StudentAttendanceDtoRequest studentAttendanceDtoRequest);

	String createStudentAttendace(StudentAttendanceDtoRequest request);
	List<StudentAttendanceDtoRequest> getAllStudentAttendance();

	StudentAttendanceDtoRequest getStudentAttendanceById(Long id);

	Page<StudentAttendanceDtoRequest> getAllStudentAttendance(Pageable pageable);

}
