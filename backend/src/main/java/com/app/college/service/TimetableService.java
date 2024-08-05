package com.app.college.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.TimetableDto;

import jakarta.validation.Valid;

public interface TimetableService {

	TimetableDto createTimetables(@Valid TimetableDto timetableDtoReq);

	TimetableDto updateTimetableById(Long id, @Valid TimetableDto timetablesDtoReq);

	String deleteTimetableById(Long id);

	TimetableDto getTimetableById(Long id);

	Page<TimetableDto> getAllTimetable(Pageable pageable);
	
	List<TimetableDto> getAllTimetable( Long courseId,  String sem,
			 String stream,  String paper,  String day, String teacherId);
	
	Map<String, List<TimetableDto>> getAllTimetableDayWise( Long courseId,  String sem,
			 String stream,  String paper,  String day, String teacherId);


	Page<TimetableDto> getTimetableBySearch(String search, Pageable pageable);

	List<?> getTimetableByDate(Long courseId, String sem, String stream, String paper, LocalDate date);

	Map<String, List<TimetableDto>> getAllTimetableByUsername(String username, String day, String teacherId);

	Map<String, Object> getTeacherTotalSubLecDayWise(String day, String username);

}
