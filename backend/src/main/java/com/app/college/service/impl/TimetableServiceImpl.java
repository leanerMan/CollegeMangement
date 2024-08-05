package com.app.college.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.college.DTO.TimetableDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Holiday;
import com.app.college.models.Student;
import com.app.college.models.Teacher;
import com.app.college.models.TimeTable;
import com.app.college.repository.HolidayRepository;
import com.app.college.repository.StudentRepository;
import com.app.college.repository.TeacherRepository;
import com.app.college.repository.TimetableRepository;
import com.app.college.service.TimetableService;

import jakarta.validation.Valid;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	private TimetableRepository timetableRepository;

	public TimetableServiceImpl(TimetableRepository timetableRepository) {
		this.timetableRepository = timetableRepository;
	}

	@Override
	public TimetableDto createTimetables(@Valid TimetableDto timetableDtoReq) {

		TimeTable timetable = modelMapper.map(timetableDtoReq, TimeTable.class);
		TimeTable saved = timetableRepository.save(timetable);
		timetableDtoReq.setId(saved.getId());
		return timetableDtoReq;
	}

	@Override
	public TimetableDto updateTimetableById(Long id, @Valid TimetableDto timetablesDtoReq) {

		if (timetableRepository.existsById(id)) {
			timetablesDtoReq.setId(id);
			TimeTable timetable = modelMapper.map(timetablesDtoReq, TimeTable.class);
			timetableRepository.save(timetable);
			return timetablesDtoReq;
		}
		throw new ConfigDataNotFound("No Timetable found with id: " + id);

	}

	@Override
	public String deleteTimetableById(Long id) {
		if (timetableRepository.existsById(id)) {
			timetableRepository.deleteById(id);
			return "Timetable deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Timetable found with id: " + id);
	}

	@Override
	public TimetableDto getTimetableById(Long id) {
		TimeTable timetable = timetableRepository.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("No Timetable found with id: " + id));
		return modelMapper.map(timetable, TimetableDto.class);
	}

	@Override
	public Page<TimetableDto> getAllTimetable(Pageable pageable) {
		return timetableRepository.findAllDto(pageable);
	}

	@Override
	public List<TimetableDto> getAllTimetable(Long courseId, String sem, String stream, String paper, String day,
			String teacherId) {

		return timetableRepository.findAllDto(courseId, sem, stream, paper, day, teacherId,null);
	}

	@Override
	public Map<String, List<TimetableDto>> getAllTimetableDayWise(Long courseId, String sem, String stream,
			String paper, String day, String teacherId) {

		Map<String, List<TimetableDto>> timetableByDay = new LinkedHashMap<>();

		List<TimetableDto> list = timetableRepository.findAllDto(courseId, sem, stream, paper, day, teacherId,null);
		System.out.println(list);
		if (day != null) {
			timetableByDay.put(day, new ArrayList<>());
		} else {
			timetableByDay.put("MONDAY", new ArrayList<>());
			timetableByDay.put("TUESDAY", new ArrayList<>());
			timetableByDay.put("WEDNESDAY", new ArrayList<>());
			timetableByDay.put("THURSDAY", new ArrayList<>());
			timetableByDay.put("FRIDAY", new ArrayList<>());
			timetableByDay.put("SATURDAY", new ArrayList<>());
		}

//		timetableByDay.put("SUNDAY", new ArrayList<>());

		// Group timetable entries by day
		for (TimetableDto entry : list) {
			String days = entry.getDay();
			timetableByDay.get(days).add(entry);
		}

		return timetableByDay;
	}

	@Override
	public Page<TimetableDto> getTimetableBySearch(String search, Pageable pageable) {
//		return timetableRepository.getTimetableBySearch(search, pageable);
		return null;
	}

	@Override
	public List<?> getTimetableByDate(Long courseId, String sem, String stream, String paper, LocalDate date) {
		String day = date.getDayOfWeek().toString();

		List<Holiday> holidays = holidayRepository.findHolidaysContainingDate(date);
		if (CollectionUtils.isEmpty(holidays)) {
			return timetableRepository.findAllDto(courseId, sem, stream, paper, day, null,null);
		}
		return holidays;

	}

	@Override
	public Map<String, List<TimetableDto>> getAllTimetableByUsername(String username,String day, String teacherId) {
		Student student = studentRepo.findByUsername(username)
				.orElseThrow(() -> new ConfigDataNotFound("No student found with  username : " + username));
		Map<String, List<TimetableDto>> timetableByDay = new LinkedHashMap<>();

//		List<TimetableDto> list = timetableRepository.findAllDto(student.getCourse().getId(), student.getSem(),
//				student.getStream(), student.getPaper(), day, teacherId);
		
		List<TimetableDto> list = timetableRepository.findAllDto(null, null,
				null, null, day, teacherId, student.getDepartment().getId());

		if (day != null) {
			timetableByDay.put(day, new ArrayList<>());
		} else {
			timetableByDay.put("MONDAY", new ArrayList<>());
			timetableByDay.put("TUESDAY", new ArrayList<>());
			timetableByDay.put("WEDNESDAY", new ArrayList<>());
			timetableByDay.put("THURSDAY", new ArrayList<>());
			timetableByDay.put("FRIDAY", new ArrayList<>());
			timetableByDay.put("SATURDAY", new ArrayList<>());
		}

		for (TimetableDto entry : list) {
			String days = entry.getDay();
			timetableByDay.get(days).add(entry);
		}
		return timetableByDay;

	}

//	@Override
//	public Map<String,Long> getTeacherTotalSubLecDayWise(String day, String username) {
//		if(teacherRepository.exitsByUsername(username)) {
//		List<TimetableDto> teacherTotalSubLecDayWise = timetableRepository.getTeacherTotalSubLecDayWise(day, username);
//		System.out.println(teacherTotalSubLecDayWise);
//		long totalLectures = teacherTotalSubLecDayWise.size();
//        long totalSubjects = teacherTotalSubLecDayWise.stream().map(TimetableDto::getCourseId).distinct().count();
//        Map<String,Long> dataObject= new HashMap<>();
//        dataObject.put("totalLecture", totalLectures);
//        dataObject.put("totalSubject", totalSubjects);
//		return dataObject;
//		}else {
//			 throw new ConfigDataNotFound("No Teacher found with  username : " + username);
//		}
//	}
	
	@Override
	public Map<String, Object> getTeacherTotalSubLecDayWise(String day, String username) {
		if(teacherRepository.exitsByUsername(username)) {
	        List<TimetableDto> teacherTotalSubLecDayWise = timetableRepository.getTeacherTotalSubLecDayWise(day, username);

//	        // Initialize lists to store timing, room numbers, and buildings
//	        List<String> timingList = new ArrayList<>();
//	        List<String> roomNoList = new ArrayList<>();
//	        List<String> buildingList = new ArrayList<>();
//
//	        // Populate lists with data from TimetableDto
//	        for (TimetableDto dto : teacherTotalSubLecDayWise) {
//	            timingList.add(dto.getStartTime().toString());
//	            roomNoList.add(dto.getRoomNo());
//	            buildingList.add(dto.getBuilding());
//	        }
//
//	        // Sort the lists
//	        Collections.sort(timingList);
//	        Collections.sort(roomNoList);
//	        Collections.sort(buildingList);

	        // Get distinct course IDs
	        long totalSubjects = teacherTotalSubLecDayWise.stream().map(TimetableDto::getCourseId).distinct().count();

	        // Construct the response map
	        Map<String, Object> response = new HashMap<>();
	        response.put("totalLecture", Map.of(
	            "count", (long) teacherTotalSubLecDayWise.size(),
	            "timing", teacherTotalSubLecDayWise.stream().map(TimetableDto::getStartTime).collect(Collectors.toList()),
	            "roomNo", teacherTotalSubLecDayWise.stream().map(TimetableDto::getRoomNo).collect(Collectors.toList()),
	            "building", teacherTotalSubLecDayWise.stream().map(TimetableDto::getBuilding).collect(Collectors.toList())
	        ));
	        response.put("totalSubject", Map.of(
	            "count", totalSubjects,
	            "totalCourse", teacherTotalSubLecDayWise.stream()
	                .map(TimetableDto::getCourseName)
	                .collect(Collectors.toList()),
                "timing", teacherTotalSubLecDayWise.stream().map(TimetableDto::getStartTime).collect(Collectors.toList())
	        ));

	        return response;
	    } else {
	        throw new ConfigDataNotFound("No Teacher found with username : " + username);
	    }
	}


}
