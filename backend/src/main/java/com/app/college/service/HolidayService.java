package com.app.college.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.HolidayDto;
import com.app.college.models.Holiday;

import jakarta.validation.Valid;

public interface HolidayService {

	Page<Holiday> getAllHolidays(Pageable pageable);

    String deleteHolidayById(Long id);

    Holiday getHolidayById(Long id);

    Page<Holiday> getHolidayBySearch(String search, Pageable pageable);

    Holiday createHoliday(@Valid HolidayDto holidayDto);

    Holiday updateHolidayById(@Valid HolidayDto holidayDto);
}
