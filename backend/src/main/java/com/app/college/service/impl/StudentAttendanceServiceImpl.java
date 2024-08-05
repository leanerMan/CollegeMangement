package com.app.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.AttendanceMapper;
import com.app.college.models.StudentAttendance;
import com.app.college.repository.StudentAttendanceRepository;
import com.app.college.service.StudentAttendanceService;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AttendanceMapper attendanceMapper;

	private StudentAttendanceRepository studentAttendanceRepo;

	public StudentAttendanceServiceImpl(StudentAttendanceRepository studentAttendanceRepository) {
		this.studentAttendanceRepo = studentAttendanceRepository;
	}
//
//	@Autowired
//	private StudentRepository studentRepo;
	


//	@Override
//	public StudentAttendanceDtoRequest createStudentAttendace(
//			@Valid StudentAttendanceDtoRequest studentAttendanceDtoRequest) {
//		
//		if(studentRepo.existsById(studentAttendanceDtoRequest.getStudentId())) {
//			StudentAttendance studentAttendance = modelMapper.map(studentAttendanceDtoRequest, StudentAttendance.class);
//			StudentAttendanceDtoRequest dtoReq = modelMapper.map(studentAttendanceRepo.save(studentAttendance),
//					StudentAttendanceDtoRequest.class);
//			return dtoReq;
//		}
//		throw new ConfigDataNotFound("Student not found with Id: "+studentAttendanceDtoRequest.getStudentId());
//	}
	
	@Override
	public String createStudentAttendace(StudentAttendanceDtoRequest request) {
		

			List<StudentAttendance> studentAttendances=attendanceMapper.toStudentAttendance(request.getTimeTableId(), request.getStudentIds());
			studentAttendanceRepo.saveAll(studentAttendances);
			return studentAttendances.size() +" students attendance marked";
					

	}

	@Override
	public List<StudentAttendanceDtoRequest> getAllStudentAttendance() {

		List<StudentAttendance> listOfStuAtten = studentAttendanceRepo.findAll();
		List<StudentAttendanceDtoRequest> newDtoList = new ArrayList<>();
		for (StudentAttendance stuAtt : listOfStuAtten) {
			newDtoList.add(modelMapper.map(stuAtt, StudentAttendanceDtoRequest.class));
		}
		return newDtoList;
	}

	@Override
	public StudentAttendanceDtoRequest getStudentAttendanceById(Long id) {
		StudentAttendance studentAttendance = studentAttendanceRepo.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("Student Attendance not found with id: " + id));
		return modelMapper.map(studentAttendance, StudentAttendanceDtoRequest.class);
	}

	@Override
	public Page<StudentAttendanceDtoRequest> getAllStudentAttendance(Pageable pageable) {
	    Page<StudentAttendance> studentAtt = studentAttendanceRepo.findAll(pageable);
	    List<StudentAttendanceDtoRequest> dtoList = new ArrayList<>();
	    for (StudentAttendance studentAttendance : studentAtt.getContent()) {
	        StudentAttendanceDtoRequest stuAttDto = modelMapper.map(studentAttendance, StudentAttendanceDtoRequest.class);
	        dtoList.add(stuAttDto);
	    }
	    return new PageImpl<>(dtoList, pageable, studentAtt.getTotalElements());
	}

	
	
}
