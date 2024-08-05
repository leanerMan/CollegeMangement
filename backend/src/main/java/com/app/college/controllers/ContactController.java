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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.ContactDtoRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.ContactService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/api/v1/contacts")
@RestController
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createContacts(@Valid @ModelAttribute ContactDtoRequest contactDtoRequest,
			@RequestParam(required = false) MultipartFile contactImageFile) {
		if (contactImageFile != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!",
					contactService.createContacts(contactDtoRequest, contactImageFile)), HttpStatus.CREATED);
		}
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", contactService.createContacts(contactDtoRequest, contactImageFile)),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateContactById(@PathVariable Long id,
			@Valid @ModelAttribute ContactDtoRequest contactDtoRequest,
			@RequestParam(required = false) MultipartFile contactImageFile) {
		if (contactDtoRequest != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Updated!",
					contactService.updateContactById(id, contactDtoRequest, contactImageFile)), HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Updated!",
					contactService.updateContactById(id, contactDtoRequest, contactImageFile)), HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getContactById(@PathVariable Long id){
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", contactService.getContactById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllContact(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", contactService.getAllContact(pageable)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteContactById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Deleted!", contactService.deleteContactById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> searchContact(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(required = false) String keyword) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", contactService.searchContact(pageable, keyword)), HttpStatus.OK);
	}

}
