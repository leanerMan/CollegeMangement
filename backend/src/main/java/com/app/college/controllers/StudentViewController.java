package com.app.college.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.payload.response.ApiResponse;
import com.app.college.service.StudentViewService;

@CrossOrigin
@RequestMapping("/api/v1/student-view")
@RestController
public class StudentViewController {

	private StudentViewService studentViewService;

	public StudentViewController(StudentViewService studentViewService) {
		this.studentViewService = studentViewService;
	}


	@GetMapping("/admit-card/{studentId}")
	public ResponseEntity<ApiResponse> getStuAdmidCardUgPgById(@PathVariable String studentId){
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentViewService.getStuAdmidCardUgPgById(studentId)),
				HttpStatus.OK);
	}
	
	@GetMapping("/admit-card/{studentId}/{sem}")
	public ResponseEntity<ApiResponse> getStuAdmidCardUgPgByIdAndSem(@PathVariable String studentId, @PathVariable String sem){
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentViewService.getStuAdmidCardUgPgByIdAndSem(studentId, sem)),
				HttpStatus.OK);
	}
	
	@GetMapping("/result/{rollno}")
	public ResponseEntity<ApiResponse> getStuResultById(@PathVariable String rollno){
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentViewService.getStuResultById(rollno)),
				HttpStatus.OK);
	}
	
	@GetMapping("/exam/{rollno}")
	public ResponseEntity<ApiResponse> getStuExamById(@PathVariable String rollno){
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentViewService.getStuExamById(rollno)),
				HttpStatus.OK);
	}
	
	@GetMapping("/result/{rollno}/{sem}")
	public ResponseEntity<ApiResponse> getStuResultByIdAndSem(@PathVariable String rollno, @PathVariable String sem){
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", studentViewService.getStuResultByIdAndSem(rollno, sem)),
				HttpStatus.OK);
	}
}
