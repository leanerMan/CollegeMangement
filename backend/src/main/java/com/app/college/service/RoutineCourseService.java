package com.app.college.service;

import java.util.List;

import com.app.college.models.RoutineCourse;


public interface RoutineCourseService {

	List<RoutineCourse> getAllRoutineCourse();

	RoutineCourse getRoutineCourseById(Long id);

	RoutineCourse updateRoutineCourse(RoutineCourse routineCourse);

}
