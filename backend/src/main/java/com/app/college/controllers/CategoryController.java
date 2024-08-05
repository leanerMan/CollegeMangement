package com.app.college.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.models.Category;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.CategoryService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class CategoryController {
	
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	@PostMapping("/")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", categoryService.createCategory(category)),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllCategory() {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", categoryService.getAllCategory()),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", categoryService.deleteCategoryById(id)),
				HttpStatus.OK);
	}
}
