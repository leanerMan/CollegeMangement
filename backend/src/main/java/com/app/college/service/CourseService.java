package com.app.college.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.CourseDto;
import com.app.college.models.Course;

import jakarta.validation.Valid;

public interface CourseService {

	Course createCourse(@Valid CourseDto courseDtoReq);

	Page<CourseDto> getAllCourse(Pageable pageable);

	Course updateCourseById(Long id, @Valid CourseDto courseDtoReq);

	String deleteCourseById(Long id);

	Course getCourseById(Long id);

	Page<Course> getCourseBySearch(String search,Pageable pageable);

	Map<String, List<Map<String, Object>>> getAllCourseByDepartment(Long depId);

}
