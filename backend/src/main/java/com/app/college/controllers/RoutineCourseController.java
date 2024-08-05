package com.app.college.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.models.RoutineCourse;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.RoutineCourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/routine-course")
@CrossOrigin
public class RoutineCourseController {

	private RoutineCourseService routineCourseService;

	public RoutineCourseController(RoutineCourseService routineCourseService) {
		this.routineCourseService = routineCourseService;
	}


	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllRoutineCourse() {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", routineCourseService.getAllRoutineCourse()),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getRoutineCourseById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", routineCourseService.getRoutineCourseById(id)),
				HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<ApiResponse> updateRoutineCourse(@Valid @RequestBody RoutineCourse routineCourse) {
	    return new ResponseEntity<>(new ApiResponse(false, "Updated!", routineCourseService.updateRoutineCourse(routineCourse)), HttpStatus.OK);
	}
}
