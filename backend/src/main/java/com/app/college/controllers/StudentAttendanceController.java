package com.app.college.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.StudentAttendanceService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/api/v1/student-attendance")
@RestController
public class StudentAttendanceController {

	private StudentAttendanceService studentAttendanceService;

	public StudentAttendanceController(StudentAttendanceService studentAttendanceService) {
		this.studentAttendanceService = studentAttendanceService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createStudentAttendace(@Valid @RequestBody StudentAttendanceDtoRequest request) {
		
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentAttendanceService.createStudentAttendace(request)),
				HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllStudentAttendance() {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentAttendanceService.getAllStudentAttendance()), HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllStudentAttendance(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentAttendanceService.getAllStudentAttendance(pageable)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStudentAttendanceById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentAttendanceService.getStudentAttendanceById(id)),
				HttpStatus.OK);
	}

}
