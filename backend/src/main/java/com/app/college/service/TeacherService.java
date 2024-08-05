package com.app.college.service;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.StaffAttendanceDto;
import com.app.college.DTO.TeacherDtoRequest;
import com.app.college.models.Teacher;

import jakarta.validation.Valid;

public interface TeacherService {


	TeacherDtoRequest updateTeacherById(Long id, @Valid TeacherDtoRequest teacherDtoReq,MultipartFile profilePhotoFile);

	String deleteTeacherById(Long id);

	TeacherDtoRequest getTeacherById(Long id);

	List<TeacherDtoRequest> getAllTeacher();

	TeacherDtoRequest createTeachersWithProfilePhoto(@Valid TeacherDtoRequest teacherDtoReq, MultipartFile profilePhotoFile);

	Page<TeacherDtoRequest> getAllTeachers(Pageable pageable);

	Page<Teacher> searchTeachers(Pageable pageable, String keyword);

	Teacher getTeacherByUsername(String username);

	List<StaffAttendanceDto> getAttendaceByTeacherId(Long id);

	Object getAllTeacherAttendance(Pageable pageable, YearMonth yearMonth);

	

}
