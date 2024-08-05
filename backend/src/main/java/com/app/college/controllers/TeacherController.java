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

import com.app.college.DTO.TeacherDtoRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/teachers")
@CrossOrigin
public class TeacherController {

	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}


	@PostMapping("/withPhoto")
	public ResponseEntity<ApiResponse> createTeachersWithProfilePhoto(
			@Valid @ModelAttribute TeacherDtoRequest teacherDtoReq,
			@RequestParam(required = false) MultipartFile profilePhotoFile) {
		System.out.println("-------------------- DATE " + teacherDtoReq.getJoiningDate());
		if (profilePhotoFile != null) {
			System.out.println(profilePhotoFile);

		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!",
				teacherService.createTeachersWithProfilePhoto(teacherDtoReq, profilePhotoFile)), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateTeacherById(@PathVariable Long id, @Valid @ModelAttribute TeacherDtoRequest teacherDtoReq,
			@RequestParam(required = false) MultipartFile profilePhotoFile) {
		if (profilePhotoFile != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Updated!",
					teacherService.updateTeacherById(id,teacherDtoReq, profilePhotoFile)), HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Updated!",
					teacherService.updateTeacherById(id,teacherDtoReq, profilePhotoFile)), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteTeacherById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Deleted!", teacherService.deleteTeacherById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getTeacherById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", teacherService.getTeacherById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllTeacher() {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", teacherService.getAllTeacher()),
				HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllTeachers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", teacherService.getAllTeachers(pageable)), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> searchTeachers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String keyword) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", teacherService.searchTeachers(pageable, keyword)), HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<ApiResponse> getStudentById(@PathVariable String username) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", teacherService.getTeacherByUsername(username)),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/attendance/")
	public ResponseEntity<ApiResponse> getAttendaceByTeacherId(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", teacherService.getAttendaceByTeacherId(id)),
				HttpStatus.OK);
	}
	
	@GetMapping("/attendance")
	public ResponseEntity<ApiResponse> getAllTeacherAttendance(@RequestParam(defaultValue = "0") int page,@RequestParam(required = false) YearMonth yearMonth,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Pageable pageable = PageRequest.of(page, size);
		if(yearMonth==null)
			yearMonth=YearMonth.now();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", teacherService.getAllTeacherAttendance(pageable, yearMonth)), HttpStatus.OK);
	}

}
