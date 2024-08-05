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

import com.app.college.DTO.StaffAttendanceDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.StaffAttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/staff-attendance")
@CrossOrigin
public class StaffAttendanceController {

	private StaffAttendanceService staffAttendanceService;
	
	public StaffAttendanceController(StaffAttendanceService staffAttendanceService) {
		this.staffAttendanceService = staffAttendanceService;
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> createStaffAttendance(@Valid @RequestBody StaffAttendanceDto staffAttendanceDto) {
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", staffAttendanceService.createStaffAttendance(staffAttendanceDto)), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllStaffAttendance(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", staffAttendanceService.getAllStaffAttendance(pageable)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStaffAttendanceById(@PathVariable Long id) {
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", staffAttendanceService.getStaffAttendanceById(id)), HttpStatus.OK);
	}
	
	@GetMapping("/by-staffid/")
	public ResponseEntity<ApiResponse> getStaffAttendanceByStaffId(@RequestParam Long staffId,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", staffAttendanceService.getStaffAttendanceByStaffId(staffId,pageable)), HttpStatus.OK);
	}
}
