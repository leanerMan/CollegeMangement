package com.app.college.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.FeesDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Fees;
import com.app.college.repository.FeesRepository;
import com.app.college.service.FeesService;

import jakarta.validation.Valid;

@Service
public class FeesServiceImpl implements FeesService {

	@Autowired
	private FeesRepository feesRepository;
	
	
	@Override
	public Page<Fees> getAllFees(Pageable pageable) {
		
		return feesRepository.findAll(pageable);
	}

	@Override
	public String deleteFeesById(Long id) {
		if(feesRepository.existsById(id)) {
			feesRepository.deleteById(id);
			return "Seccesfully deleted fees with Id:- "+id;
		}
	 throw new ConfigDataNotFound("Fees not found with Id:- "+id);
	}

	@Override
	public Fees getFeesById(Long id) {
		if(feesRepository.existsById(id)) {
			return feesRepository.findById(id).get();
		}
	 throw new ConfigDataNotFound("Fees not found with Id:- "+id);
	}

	@Override
	public Page<Fees> getFeesBySearch(String search, Pageable pageable) {
		return feesRepository.getFeesBySearch(search,pageable);
	}

	@Override
	public Fees createFees(@Valid FeesDto feesDto) {
		Fees fee = new Fees();
		BeanUtils.copyProperties(feesDto, fee);
		return feesRepository.save(fee);
	}

	@Override
	public Fees updateFeesById(@Valid FeesDto feesDto) {
		Optional<Fees> findById = feesRepository.findById(feesDto.getId());
		if(findById.isPresent()) {
			Fees fees = findById.get();
			BeanUtils.copyProperties(feesDto, fees);
			return feesRepository.save(fees);
		}
	 throw new ConfigDataNotFound("Fees not found with Id:- "+feesDto.getId());
	}

}
