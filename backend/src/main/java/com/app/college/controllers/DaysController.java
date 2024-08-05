package com.app.college.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.models.Days;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.DaysService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/days")
@CrossOrigin
public class DaysController {

	private DaysService DaysService;
	
	public DaysController(DaysService DaysService) {
		this.DaysService = DaysService;
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllRoutine() {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", DaysService.getAllRoutine()),
				HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> addRoutine(@Valid @RequestBody Days Days) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", DaysService.addRoutine(Days)),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getAllRoutineById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", DaysService.getAllRoutineById(id)),
				HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<ApiResponse> updateRoutine(@Valid @RequestBody Days Days) {
	    return new ResponseEntity<>(new ApiResponse(false, "Updated!", DaysService.updateRoutine(Days)), HttpStatus.OK);
	}
}
