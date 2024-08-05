package com.app.college.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.FeesDto;
import com.app.college.models.Fees;

import jakarta.validation.Valid;

public interface FeesService {

	Page<Fees> getAllFees(Pageable pageable);

	String deleteFeesById(Long id);

	Fees getFeesById(Long id);

	Page<Fees> getFeesBySearch(String search, Pageable pageable);

	Fees createFees(@Valid FeesDto feesDto);

	Fees updateFeesById(@Valid FeesDto feesDto);

}
