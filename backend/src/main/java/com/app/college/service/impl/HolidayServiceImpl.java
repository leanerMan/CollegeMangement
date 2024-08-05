package com.app.college.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.HolidayDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Holiday;
import com.app.college.repository.HolidayRepository;
import com.app.college.service.HolidayService;

import jakarta.validation.Valid;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public Page<Holiday> getAllHolidays(Pageable pageable) {
        return holidayRepository.findAll(pageable);
    }

    @Override
    public String deleteHolidayById(Long id) {
        if (holidayRepository.existsById(id)) {
            holidayRepository.deleteById(id);
            return "Successfully deleted holiday with Id:- " + id;
        }
        throw new ConfigDataNotFound("Holiday not found with Id:- " + id);
    }

    @Override
    public Holiday getHolidayById(Long id) {
        if (holidayRepository.existsById(id)) {
            return holidayRepository.findById(id).get();
        }
        throw new ConfigDataNotFound("Holiday not found with Id:- " + id);
    }

    @Override
    public Page<Holiday> getHolidayBySearch(String search,Pageable pageable) {
        return holidayRepository.getHolidayBySearch(search, pageable);
    }

    @Override
    public Holiday createHoliday(@Valid HolidayDto holidayDto) {
        Holiday holiday = new Holiday();
        BeanUtils.copyProperties(holidayDto, holiday);
        return holidayRepository.save(holiday);
    }

    @Override
    public Holiday updateHolidayById(@Valid HolidayDto holidayDto) {
        Optional<Holiday> findById = holidayRepository.findById(holidayDto.getId());
        if (findById.isPresent()) {
            Holiday holiday = findById.get();
            BeanUtils.copyProperties(holidayDto, holiday, "holidayGenDate");
            return holidayRepository.save(holiday);
        }
        throw new ConfigDataNotFound("Holiday not found with Id:- " + holidayDto.getId());
    }
}

