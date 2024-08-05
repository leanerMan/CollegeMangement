package com.app.college.service;

import java.util.List;

import com.app.college.models.Days;

public interface DaysService {

	List<Days> getAllRoutine();

	Days getAllRoutineById(Long id);

	Days updateRoutine(Days Days);

	Days addRoutine(Days Days);

}
