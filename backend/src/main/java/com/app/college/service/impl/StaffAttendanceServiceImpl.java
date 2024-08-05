package com.app.college.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.StaffAttendanceDto;
import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.AttendanceMapper;
import com.app.college.models.StaffAttendance;
import com.app.college.repository.StaffAttendanceRepository;
import com.app.college.repository.StaffRepository;
import com.app.college.repository.TeacherRepository;
import com.app.college.service.StaffAttendanceService;

@Service
public class StaffAttendanceServiceImpl implements StaffAttendanceService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	private StaffAttendanceRepository staffAttendanceRepository;
	private StaffRepository staffRepository;
	private TeacherRepository teacherRepository;

	public StaffAttendanceServiceImpl(StaffAttendanceRepository staffAttendanceRepository,
			StaffRepository staffRepository, TeacherRepository teacherRepository) {
		this.staffAttendanceRepository = staffAttendanceRepository;
		this.staffRepository = staffRepository;
		this.teacherRepository = teacherRepository;
	}

	@Override
	public String createStaffAttendance(StaffAttendanceDto staffAttendanceDto) {
		StaffAttendance attendance = attendanceMapper.toTeacherAttendance(staffAttendanceDto.getTimeTableId(), staffAttendanceDto.getTeacherId());
		staffAttendanceRepository.save(attendance);
		return "attendance marked!!!";
	}

	@Override
	public Page<StaffAttendance> getAllStaffAttendance(Pageable pageable) {
		return staffAttendanceRepository.findAll(pageable);
	}

	@Override
	public StaffAttendanceDto getStaffAttendanceById(Long id) {
		StaffAttendance attendance = staffAttendanceRepository.findById(id).orElseThrow(() -> new ConfigDataNotFound("Teacher Attendance not found with id: " + id));
		return modelMapper.map(attendance, StaffAttendanceDto.class);
	}

	@Override
	public Object getStaffAttendanceByStaffId(Long staffId, Pageable pageable) {
//		if (staffRepository.existsById(staffId) || teacherRepository.existsById(Long.valueOf(staffId))) {
//			Page<StaffAttendance> findByStaffId = staffAttendanceRepository.findByStaffId(staffId, pageable);
//			Map<String,Object> map = new HashMap<>();
//			map.put("StaffAttendanceDetails", findByStaffId);
//			if(staffRepository.existsById(staffId)) {
//				map.put("StaffDetails", staffRepository.findById(staffId).get());
//			}else {
//				map.put("StaffDetails", teacherRepository.findById(Long.valueOf(staffId)));
//			}
//		return map;
//		}
	    throw new ConfigDataNotFound("No Staff found with id: " + staffId);
	}
	
	public Page<StaffAttendanceDto> getAllStaffAttendanceForMob(Pageable pageable) {
		 Page<StaffAttendance> all = staffAttendanceRepository.findAll(pageable);
		 List<StaffAttendanceDto> dtoList = new ArrayList<>();
		 for(StaffAttendance staffAttendance: all.getContent()) {
			 dtoList.add(modelMapper.map(staffAttendance, StaffAttendanceDto.class));
		 }
		 return new PageImpl<>(dtoList, pageable, all.getTotalElements());
	}

}
