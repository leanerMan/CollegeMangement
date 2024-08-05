package com.app.college.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.NoticeDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.NoticeService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/api/v1/notice")
@RestController
public class NoticeController {
	
	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	@PostMapping("/")
	public ResponseEntity<ApiResponse> createNotice(@Valid @ModelAttribute NoticeDto noticeDto,
			@RequestParam(required = false) MultipartFile noticeImgPdfFile) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", noticeService.createNotice(noticeDto, noticeImgPdfFile)),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllNotice(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long departmentId) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", noticeService.getAllNotice(pageable, categoryId, departmentId)), HttpStatus.OK);
	}
	
	@GetMapping("/app/")
	public ResponseEntity<ApiResponse> getAllNoticeForApp(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long departmentId) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", noticeService.getAllNoticeForApp(pageable, categoryId, departmentId)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getNoticeById(@PathVariable Long id){
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", noticeService.getNoticeById(id)),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteNoticeById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Deleted!", noticeService.deleteNoticeById(id)),
				HttpStatus.OK);
	}
}
