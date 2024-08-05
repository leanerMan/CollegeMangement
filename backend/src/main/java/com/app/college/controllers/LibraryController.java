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

import com.app.college.DTO.LibraryDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/library")
@CrossOrigin
public class LibraryController {

	private LibraryService libraryService;

	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createLibrary(@Valid @RequestBody LibraryDto libraryDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", libraryService.createLibrary(libraryDtoReq)), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllLibrary(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", libraryService.getAllLibrary(pageable)),
				HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateLibraryById(@PathVariable Long id,
			@Valid @RequestBody LibraryDto libraryDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Updated!", libraryService.updateStudentById(id, libraryDtoReq)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteLibraryById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", libraryService.deleteLibraryById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getLibraryById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", libraryService.getLibraryById(id)),
				HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getLibraryBySearch(@RequestParam(required=false) String search, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", libraryService.getLibraryBySearch(search, pageable)),
				HttpStatus.OK);
	}
}
