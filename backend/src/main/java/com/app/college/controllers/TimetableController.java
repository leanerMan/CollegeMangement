package com.app.college.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

import com.app.college.DTO.TimetableDto;
import com.app.college.models.Holiday;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.TimetableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/timetable")
@CrossOrigin
public class TimetableController {

	private TimetableService timetableService;

	public TimetableController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse> createTimetables(@Valid @RequestBody TimetableDto timetableDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Success!", timetableService.createTimetables(timetableDtoReq)), HttpStatus.OK);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateTimetableById(@PathVariable Long id,
			@Valid @RequestBody TimetableDto timetablesDtoReq) {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, "Updated!", timetableService.updateTimetableById(id, timetablesDtoReq)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteTimetableById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.deleteTimetableById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getTimetableById(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getTimetableById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getAllTimetable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getAllTimetable(pageable)),
				HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllTimetable(@RequestParam(required = false) Long courseId, @RequestParam(required = false) String sem,
			@RequestParam(required = false) String stream, @RequestParam(required = false) String paper, @RequestParam(required = false) String day,@RequestParam(required = false) String teacherId) {

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getAllTimetableDayWise( courseId, sem, stream, paper,  day,teacherId)),
				HttpStatus.OK);
	}
	
	@GetMapping("/date")
	public ResponseEntity<ApiResponse> getTimetableOfDate(@RequestParam(required = false) Long courseId, @RequestParam(required = false) String sem,
			@RequestParam(required = false) String stream, @RequestParam(required = false) String paper, @RequestParam(required = false) LocalDate date) {
	
		 List<?> objects=timetableService.getTimetableByDate( courseId, sem, stream, paper,date);
		 if (!CollectionUtils.isEmpty(objects)) {
	            if (objects.get(0) instanceof TimetableDto) {
	                return new ResponseEntity<>(new ApiResponse(false, "Success!", objects), HttpStatus.OK);
	            } else if (objects.get(0) instanceof Holiday) {
	                return new ResponseEntity<>(new ApiResponse(true, "Holidays found!", objects), HttpStatus.OK);
	            }
	        }

	        return new ResponseEntity<>(new ApiResponse(true, "No data found!", null), HttpStatus.NOT_FOUND);
	}
	
	
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getTimetableBySearch(@RequestParam(required=false) String search,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String[] sort,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Sort.Direction direction=Sort.Direction.fromString(sortDirection);
		Pageable pageable=PageRequest.of(page, size, direction, sort);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getTimetableBySearch(search, pageable)),
				HttpStatus.OK);
	}
	
	@GetMapping("/student")
	public ResponseEntity<ApiResponse> getAllTimetableOfStudent(@RequestParam String username, @RequestParam(required = false) String day,@RequestParam(required = false) String teacherId) {

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getAllTimetableByUsername( username,  day,teacherId)),
				HttpStatus.OK);
	}
	
	@GetMapping("/teacher")
	public ResponseEntity<ApiResponse> getAllTimetableOfStudent(@RequestParam(required = false) Long courseId, @RequestParam(required = false) String sem,
			@RequestParam(required = false) String stream, @RequestParam(required = false) String paper, @RequestParam(required = false) String day,@RequestParam(required = false) String username) {

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getAllTimetableDayWise( courseId, sem, stream, paper,  day,username)),
				HttpStatus.OK);
	}
	
	@GetMapping("/teacher-total-sub-lec")
	public ResponseEntity<ApiResponse> getTeacherTotalSubLecDayWise(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,@RequestParam String username) {
		String day = (date != null) ? date.getDayOfWeek().toString() : LocalDate.now().getDayOfWeek().toString();
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", timetableService.getTeacherTotalSubLecDayWise(day,username)),
				HttpStatus.OK);
	}
	

}
