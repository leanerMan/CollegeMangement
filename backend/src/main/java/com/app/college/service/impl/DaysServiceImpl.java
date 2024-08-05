package com.app.college.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Days;
import com.app.college.repository.DaysRepository;
import com.app.college.service.DaysService;


@Service
public class DaysServiceImpl implements DaysService {
	
	private DaysRepository DaysRepo;

	public DaysServiceImpl(DaysRepository DaysRepo) {
		this.DaysRepo = DaysRepo;
	}

	@Override
	public List<Days> getAllRoutine() {
		return DaysRepo.findAll();
	}

	@Override
	public Days getAllRoutineById(Long id) {
		return DaysRepo.findById(id).orElseThrow(()-> new ConfigDataNotFound("Days-Id not found in dataBase"));
	}

	@Override
	public Days updateRoutine(Days Days) {
		if(!DaysRepo.existsById(Days.getId())) {
			throw new ConfigDataNotFound("Days-Id not found in dataBase");
		}
		return DaysRepo.save(Days);
	}

	@Override
	public Days addRoutine(Days Days) {
		return DaysRepo.save(Days);
	}

}
