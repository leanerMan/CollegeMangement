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

import com.app.college.DTO.FeesDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.FeesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/fees")
@CrossOrigin
public class FeesController {

	private FeesService feesService;
	
	public FeesController(FeesService feesService) {
		this.feesService = feesService;
	}


	@PostMapping("/")
	public ResponseEntity<ApiResponse> createFees(@Valid @RequestBody FeesDto feesDto) {
	    return new ResponseEntity<>(new ApiResponse(false, "Success!", feesService.createFees(feesDto)), HttpStatus.OK);
	}

	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllFees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", feesService.getAllFees(pageable)),
				HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<ApiResponse> updateFeesById(@Valid @RequestBody FeesDto feesDto) {
	    return new ResponseEntity<>(new ApiResponse(false, "Updated!", feesService.updateFeesById(feesDto)), HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteFeesById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", feesService.deleteFeesById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getFeesById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", feesService.getFeesById(id)),
				HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getFeesBySearch(@RequestParam String search, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", feesService.getFeesBySearch(search, pageable)),
				HttpStatus.OK);
	}
}
