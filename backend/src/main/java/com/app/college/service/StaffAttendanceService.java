package com.app.college.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.StaffAttendanceDto;
import com.app.college.models.StaffAttendance;

public interface StaffAttendanceService {

	String createStaffAttendance(StaffAttendanceDto staffAttendanceDto);

	Page<StaffAttendance> getAllStaffAttendance(Pageable pageable);

	StaffAttendanceDto getStaffAttendanceById(Long id);

	Object getStaffAttendanceByStaffId(Long staffId, Pageable pageable);
}
