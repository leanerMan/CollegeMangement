package com.app.college.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.CourseDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Course;
import com.app.college.models.Teacher;
import com.app.college.repository.CourseRepository;
import com.app.college.service.CourseService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;
	
	private ModelMapper modelMapper;

	public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
		this.courseRepository = courseRepository;
		this.modelMapper= modelMapper;
	}

	@Override
	public Course createCourse(@Valid CourseDto courseDto) {
		Course course = modelMapper.map(courseDto, Course.class);
        return courseRepository.save(course);
	}

//	@Override
//	public List<CourseDto> getAllCourse() {
//	    List<Course> courses = courseRepository.findAll();
//	    List<CourseDto> courseDtos = new ArrayList<>();
//	    for (Course course : courses) {
//	        CourseDto courseDto = new CourseDto();
//	        BeanUtils.copyProperties(course, courseDto);
//	        courseDtos.add(courseDto);
//	    }
//	    return courseDtos;
//	}
	
	@Override
	public Page<CourseDto> getAllCourse(Pageable pageable) {
	    Page<Course> courses = courseRepository.findAll(pageable);
	    List<CourseDto> courseDtos = new ArrayList<>();
	    for (Course course : courses) {
	        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
	        courseDtos.add(courseDto);
	    }
	    return new PageImpl<>(courseDtos, pageable, courses.getTotalElements());
	}



	@Override
	public Course updateCourseById(Long id, @Valid CourseDto courseDto) {
		if (courseRepository.existsById(id)) {
//			Course course = new Course();
//	        BeanUtils.copyProperties(courseDto, course);
			Course course = modelMapper.map(courseDto, Course.class);
	        course.setId(id);
			return courseRepository.save(course);
		}
		throw new ConfigDataNotFound("No Course found with id: " + id);
	}

	@Override
	public String deleteCourseById(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			return "Course deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Course found with id: " + id);
	}

	@Override
	public Course getCourseById(Long id) {
		if (courseRepository.existsById(id)) {
			return courseRepository.findById(id).get();
		}
		throw new ConfigDataNotFound("No Course found with id: " + id);
	}

	@Override
	public Page<Course> getCourseBySearch(String search,Pageable pageable) {
		return courseRepository.getCourseBySearch(search,pageable);
	}

	@Override
	public Map<String, List<Map<String, Object>>> getAllCourseByDepartment(Long depId) {
	    List<Course> allCourses = courseRepository.getAllCourseByDepartment(depId);
	    Map<String, List<Map<String, Object>>> result = new HashMap<>();

	    for (Course course : allCourses) {
	        String semKey = "Semester " + course.getSemCode();
	        if (!result.containsKey(semKey)) {
	            result.put(semKey, new ArrayList<>());
	        }

	        Map<String, Object> courseMap = new HashMap<>();
	        courseMap.put("courseId", course.getId());
	        courseMap.put("courseName", course.getCourseName());
	        courseMap.put("courseCode", course.getCourseCode());
	        courseMap.put("startDate", course.getStartDate());
	        courseMap.put("courseTimeLength", course.getCourseTimeLength());
	        courseMap.put("courseDetails", course.getCourseDetails());
	        courseMap.put("courseStatus", course.getCourseStatus());
	        courseMap.put("semester", course.getSemCode());

	        List<Map<String, Object>> teachersList = new ArrayList<>();
	        for (Teacher teacher : course.getTeacher()) {
	            Map<String, Object> teacherMap = new HashMap<>();
	            teacherMap.put("teacherFullName", teacher.getFirstName() + " " + teacher.getLastName());
	            teacherMap.put("teacherDesignation", teacher.getDesignation());
	            teacherMap.put("teacherDegree", teacher.getDegree());
	            teacherMap.put("profilePhoto", teacher.getProfilePhoto());
	            teachersList.add(teacherMap);
	        }
	        courseMap.put("teacher", teachersList);

	        result.get(semKey).add(courseMap);
	    }

	    return result;
	}


}
