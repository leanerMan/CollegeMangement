package com.app.college.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.DTO.CourseDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.repository.CourseRepository;
import com.app.college.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin
public class CourseController {

	private CourseService courseService;
	
	private CourseRepository courseRepository;

	public CourseController(CourseService courseService, CourseRepository courseRepository) {
		this.courseService = courseService;
		this.courseRepository = courseRepository;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createCourse(@Valid @RequestBody CourseDto courseDtoReq) {
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", courseService.createCourse(courseDtoReq)), HttpStatus.OK);
	}

	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllCourse(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", courseService.getAllCourse(pageable)),
				HttpStatus.OK);
	}
	
//	@GetMapping("/on-dep")
//	public ResponseEntity<ApiResponse> getAllCourseByDepartment(@RequestParam(required = false) Long depId) {
//		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", courseService.getAllCourseByDepartment(depId)),
//				HttpStatus.OK);
//	}
	
	@GetMapping("/on-dep")
	public ResponseEntity<ApiResponse> getAllCourseByDepartmentAndSemester(
	    @RequestParam(required = true) String username,@RequestParam(required = false) Integer semCode) {
	    
		Long depId = courseRepository.findDepIdByUsername(username);
		
	    if (depId == null) {
	        // Handle the case where department ID is not getting
	        return new ResponseEntity<>(new ApiResponse(false, "This user's name did not retrieve the Department ID !!!", depId), HttpStatus.BAD_REQUEST);
	    }

	    Map<String, List<Map<String, Object>>> coursesByDepartmentAndSemester = courseService.getAllCourseByDepartment(depId);
	    
	    // If semester code is provided, filter the courses by that semester
	    if (semCode != null) {
	        String semKey = "Semester " + semCode;
	        if (!coursesByDepartmentAndSemester.containsKey(semKey)) {
	            return new ResponseEntity<>(new ApiResponse(false, "No courses found for the specified department and semester",null), HttpStatus.NOT_FOUND);
	        }

	        Map<String, List<Map<String, Object>>> filteredCourses = new HashMap<>();
	        filteredCourses.put(semKey, coursesByDepartmentAndSemester.get(semKey));
	        return new ResponseEntity<>(new ApiResponse(false, "Success!", filteredCourses), HttpStatus.OK);
	    }

	    // If semester code is not provided, return all courses for the department
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", coursesByDepartmentAndSemester), HttpStatus.OK);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCourseById(@PathVariable Long id,
	        @Valid @RequestBody CourseDto courseDtoReq) {
	    return new ResponseEntity<>(new ApiResponse(false, "Updated!", courseService.updateCourseById(id, courseDtoReq)), HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCourseById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", courseService.deleteCourseById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getCourseById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", courseService.getCourseById(id)),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getAllCourse(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection,@RequestParam String keyword) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", courseService.getCourseBySearch(keyword,pageable)),
				HttpStatus.OK);
	}
}
