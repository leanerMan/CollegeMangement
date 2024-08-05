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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.StaffDtoRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.StaffService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/staffs")
@CrossOrigin
public class StaffController {

	private StaffService staffService;
	
	public StaffController(StaffService staffService) {
		this.staffService=staffService;
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> createStaffs(@Valid @RequestBody StaffDtoRequest staffDtoReq) {

		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", staffService.createStaffs(staffDtoReq)), HttpStatus.OK);
	}

	 @PostMapping("/withphoto")
	    public ResponseEntity<ApiResponse> createStaffsWithProfilePhoto(
	            @Valid  StaffDtoRequest staffDtoReq,
	            @RequestPart(required = false) MultipartFile profilePhotoFile) {
	        return new ResponseEntity<ApiResponse>(
					new ApiResponse(false, "Success!", staffService.createStaffsWithPhoto(staffDtoReq, profilePhotoFile)), HttpStatus.OK);
	    }

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateStaffById(@PathVariable Long id,
			@Valid @RequestBody StaffDtoRequest staffDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", staffService.updateStaffById(id, staffDtoReq)),
				HttpStatus.OK);
	}
	
	@PutMapping("/withphoto/{id}")
	public ResponseEntity<ApiResponse> updateStaffByIdWithProfilePhoto(@PathVariable Long id,
			@Valid  StaffDtoRequest staffDtoReq, @RequestPart(required = false) MultipartFile profilePhotoFile) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", staffService.updateStaffByIdWithPhoto(id, staffDtoReq, profilePhotoFile)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStaffById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", staffService.deleteStaffById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStaffById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", staffService.getStaffById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllStaff(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", staffService.getAllStaff(pageable)),
				HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getStaffBySearch(@RequestParam String search,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", staffService.getStaffBySearch(search, pageable)),
				HttpStatus.OK);
	}
	
}
