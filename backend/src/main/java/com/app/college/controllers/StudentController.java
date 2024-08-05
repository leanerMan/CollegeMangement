package com.app.college.controllers;

import java.time.YearMonth;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.StudentDtoRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/withphoto")
	public ResponseEntity<ApiResponse> createStudentsWithProfilePhoto(
			@Valid @ModelAttribute StudentDtoRequest studentsDtoReq,
			@RequestParam(required = false) MultipartFile profilePhotoFile,
			@RequestParam(required = false) MultipartFile profileSignatureFile) {
		if (profilePhotoFile != null) {
			System.out.println("Profile Photo Filename: " + profilePhotoFile.getOriginalFilename());
		}
		if (profileSignatureFile != null) {
			System.out.println("Profile Signature Filename: " + profileSignatureFile.getOriginalFilename());
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!",
				studentService.createStudentsWithProfilePhoto(studentsDtoReq, profilePhotoFile, profileSignatureFile)),
				HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateStudentById(@PathVariable Long id, @Valid @ModelAttribute StudentDtoRequest studentsDtoReq,
			@RequestParam(required = false) MultipartFile profilePhotoFile,
			@RequestParam(required = false) MultipartFile profileSignatureFile) {
		if (profilePhotoFile != null || profileSignatureFile != null) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false, "Updated!",
							studentService.updateStudentById(id,studentsDtoReq, profilePhotoFile, profileSignatureFile)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false, "Updated!",
							studentService.updateStudentById(id,studentsDtoReq, profilePhotoFile, profileSignatureFile)),
					HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Deleted!", studentService.deleteStudentById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentService.getStudentById(id)),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<ApiResponse> getStudentById(@PathVariable String username) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentService.getStudentByUsername(username)),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllStudent() {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentService.getAllStudent()),
				HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllStudent(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentService.getAllStudent(pageable)), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> searchStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam String keyword) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentService.searchStudents(pageable, keyword)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/attendance/")
	public ResponseEntity<ApiResponse> getAttendaceByStudentId(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentService.getAttendaceByStudentId(id)),
				HttpStatus.OK);
	}
	
	@GetMapping("/attendance")
	public ResponseEntity<ApiResponse> getAllStudentAttendance(@RequestParam(defaultValue = "0") int page,@RequestParam(required = false) YearMonth yearMonth,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "asc") String sortDirection) {
//		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size);
		if(yearMonth==null)
			yearMonth=YearMonth.now();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", studentService.getAllStudentAttendance(pageable, yearMonth)), HttpStatus.OK);
	}

}
