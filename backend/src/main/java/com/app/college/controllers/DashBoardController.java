package com.app.college.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.service.DashBoardService;


@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin
public class DashBoardController {
	
	private DashBoardService dashBoardService;

	public DashBoardController(DashBoardService dashBoardService) {
		this.dashBoardService = dashBoardService;
	}

	
	@GetMapping("/data")
	 public ResponseEntity<?> getDashboardData() throws ClassCastException {
		Map<String, Object> data =  dashBoardService.getMisDashboardData();
	 
	     return ResponseEntity.ok(data);
	 }
}
