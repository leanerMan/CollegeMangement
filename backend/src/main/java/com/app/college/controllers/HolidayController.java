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

import com.app.college.DTO.HolidayDto;
import com.app.college.payload.response.ApiResponse;
import com.app.college.service.HolidayService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/holidays")
@CrossOrigin
public class HolidayController {

    private HolidayService holidayService;
    

    public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	@PostMapping("/")
    public ResponseEntity<ApiResponse> createHoliday(@Valid @RequestBody HolidayDto holidayDto) {
        return new ResponseEntity<>(new ApiResponse(false, "Success!", holidayService.createHoliday(holidayDto)), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllHolidays(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size,
                                                      @RequestParam(defaultValue = "id") String[] sort,
                                                      @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, sort);
        return new ResponseEntity<>(new ApiResponse(false, "Success!", holidayService.getAllHolidays(pageable)), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> updateHolidayById(@Valid @RequestBody HolidayDto holidayDto) {
        return new ResponseEntity<>(new ApiResponse(false, "Updated!", holidayService.updateHolidayById(holidayDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteHolidayById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse(false, "Success!", holidayService.deleteHolidayById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getHolidayById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse(false, "Success!", holidayService.getHolidayById(id)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> getHolidayBySearch(@RequestParam(required=false) String search,@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String[] sort,
            @RequestParam(defaultValue = "asc") String sortDirection) {
    	Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, sort);
        return new ResponseEntity<>(new ApiResponse(false, "Success!", holidayService.getHolidayBySearch(search, pageable)), HttpStatus.OK);
    }
}
