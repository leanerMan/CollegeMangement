package com.app.college.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.RoutineCourse;
import com.app.college.repository.RoutineCourseRepository;
import com.app.college.service.RoutineCourseService;


@Service
public class RoutineCourseServiceImpl implements RoutineCourseService {

	private RoutineCourseRepository routineCourseRepository;
	
	public RoutineCourseServiceImpl(RoutineCourseRepository routineCourseRepository) {
		this.routineCourseRepository = routineCourseRepository;
	}

	@Override
	public List<RoutineCourse> getAllRoutineCourse() {
		return routineCourseRepository.findAll();
	}

	@Override
	public RoutineCourse getRoutineCourseById(Long id) {
		return routineCourseRepository.findById(id).orElseThrow(()-> new ConfigDataNotFound("Course-Id not found in dataBase"));
	}

	@Override
	public RoutineCourse updateRoutineCourse(RoutineCourse routineCourse) {
		return routineCourseRepository.save(routineCourse);
	}

}
