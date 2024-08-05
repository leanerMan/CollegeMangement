package com.app.college.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.payload.response.ApiResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> allAccess() {
		return ResponseEntity.ok(new ApiResponse(false, "Public Content.", ""));
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('user')")
	public ResponseEntity<ApiResponse> userAccess() {
		return ResponseEntity.ok(new ApiResponse(false, "User Content.", ""));
	}

	@GetMapping("/mod")
	@PreAuthorize("hasAuthority('moderator')")
	public ResponseEntity<ApiResponse> moderatorAccess() {
		return ResponseEntity.ok(new ApiResponse(false, "Moderator Board.", ""));
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ApiResponse> adminAccess() {
		return ResponseEntity.ok(new ApiResponse(false, "Admin Board.", ""));
	}
}
