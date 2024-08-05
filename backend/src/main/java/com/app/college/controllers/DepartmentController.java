package com.app.college.controllers;

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

import com.app.college.DTO.DepartmentDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createDepartment(@Valid @RequestBody DepartmentDto departmentDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", departmentService.createDepartment(departmentDtoReq)), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllDepartment(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", departmentService.getAllDepartment(pageable)),
				HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateDepartmentById(@PathVariable Long id,
			@Valid @RequestBody DepartmentDto departmentDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Updated!", departmentService.updateStudentById(id, departmentDtoReq)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", departmentService.deleteDepartmentById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getDepartmentById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", departmentService.getDepartmentById(id)),
				HttpStatus.OK);
	}
	
	@GetMapping("/search/{search}")
	public ResponseEntity<ApiResponse> getDepartmentBySearch(@PathVariable String search) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", departmentService.getDepartmentBySearch(search)),
				HttpStatus.OK);
	}
}
