package com.app.college.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.college.models.ClassRoom;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.ClassRoomService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/api/v1/rooms")
@RestController
public class ClassRoomController {
	
	@Autowired
	private ClassRoomService classRoomService;
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> createContacts(@Valid @RequestBody ClassRoom room) {
	

		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", classRoomService.createRoom(room)),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateContactById(@PathVariable Long id,@RequestBody ClassRoom room) {

			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Updated!",
					classRoomService.updateRoomById(id, room)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getContactById(@PathVariable Long id){
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", classRoomService.getRoomById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllContact(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Pageable pageable = PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", classRoomService.getAllRooms(pageable)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteContactById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Deleted!", classRoomService.deleteRoomById(id)),
				HttpStatus.OK);
	}


}
