package com.app.college.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.StaffAttendanceDto;
import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.DTO.TeacherDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.AttendanceMapper;
import com.app.college.mapper.TeacherMapper;
import com.app.college.models.StaffAttendance;
import com.app.college.models.Teacher;
import com.app.college.repository.StaffAttendanceRepository;
import com.app.college.repository.TeacherRepository;
import com.app.college.service.TeacherService;
import com.app.college.util.DateTimeGenerator;

import jakarta.validation.Valid;

@Service
public class TeacherServiceImpl implements TeacherService {

	private TeacherRepository teacherRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	AttendanceMapper attendanceMapper;
	
	@Autowired
	StaffAttendanceRepository staffAttendanceRepo;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
	private String bucketName;
	
	
	@Override
	public TeacherDtoRequest createTeachersWithProfilePhoto(@Valid TeacherDtoRequest teacherDtoReq,
			MultipartFile profilePhotoFile) {
		Teacher teacher = teacherRepository.save(teacherMapper.toEntityWithPhoto(teacherDtoReq, profilePhotoFile));
		return teacherMapper.toDto(teacher);
	}


	@Override
	public TeacherDtoRequest updateTeacherById(Long id, @Valid TeacherDtoRequest teacherDtoReq, MultipartFile profilePhotoFile) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("No Teacher found with id " + id));

		BeanUtils.copyProperties(teacherDtoReq, teacher, "profilePhoto");
		try {
			// Check if the file is not empty
			if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
				File tempFile = File.createTempFile("temp", null);
				tempFile.deleteOnExit();
				// Transfer the content of MultipartFile to the temporary file
				profilePhotoFile.transferTo(tempFile);
				File imageFile = tempFile;
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(profilePhotoFile.getContentType());
				String fileName = teacherDtoReq.getProfilePhoto();
				String key = "teachers/" + fileName;
				teacher.setProfilePhoto(fileName);
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
				putObjectRequest.setMetadata(metadata);
				teacher.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
				 Teacher savedTeacher = teacherRepository.save(teacher);
				if (savedTeacher != null) {
					s3Client.putObject(putObjectRequest);
				}
				// Optionally, you can delete the local file after uploading to S3
				imageFile.delete();
			} else {
				teacher.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
				 teacherRepository.save(teacher);
			}
		} catch (IOException exc) {
			throw new RuntimeException("Error occurred while saving profile photo!!!", exc);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return teacherMapper.toDto(teacher);
	}

	@Override
	public String deleteTeacherById(Long id) {
		if (teacherRepository.existsById(id)) {
			teacherRepository.deleteById(id);
			return "Teacher deleted with id " + id;
		}
		throw new ConfigDataNotFound("No Teacher found with id " + id);
	}

	@Override
	public TeacherDtoRequest getTeacherById(Long id) {
		Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ConfigDataNotFound("No Teacher found with id " + id));
		return teacherMapper.toDto(teacher);
	}

	@Override
	public List<TeacherDtoRequest> getAllTeacher() {
		 List<Teacher> teachers = teacherRepository.findAll();
		 List<TeacherDtoRequest> newDtoList=new ArrayList<>();
		 for(Teacher teacher:teachers) {
			 newDtoList.add(teacherMapper.toDto(teacher));
		 }
		 return newDtoList;
	}


	@Override
	public Page<TeacherDtoRequest> getAllTeachers(Pageable pageable) {
		Page<Teacher> teachers = teacherRepository.findAll(pageable);
		 List<TeacherDtoRequest> newDtoList=new ArrayList<>();
		 for(Teacher teacher:teachers.getContent()) {
			 newDtoList.add(teacherMapper.toDto(teacher));
		 }
		 return new PageImpl<>(newDtoList, pageable, teachers.getTotalElements());
		 
	}

	@Override
	public Page<Teacher> searchTeachers(Pageable pageable, String keyword) {
		String keywordLowerCase = keyword != null ? keyword.toLowerCase() : null;
		Page<Teacher> teachers = teacherRepository.searchTeachers(keywordLowerCase, pageable);
		return teachers;
	}


	@Override
	public Teacher getTeacherByUsername(String username) { 
		return teacherRepository.findByUsername(username).orElseThrow(() -> new ConfigDataNotFound("No Teacher found with username " + username));

	}


	@Override
	public List<StaffAttendanceDto> getAttendaceByTeacherId(Long id) {
		List<StaffAttendance> attendanceById = teacherRepository.findAllAttendanceById(id);
		List<StaffAttendanceDto> newDtoList = new ArrayList<>();
		for(StaffAttendance staffAtt : attendanceById) {
			newDtoList.add(mapper.map(staffAtt, StaffAttendanceDto.class));
		}
		return newDtoList;
	}


	@Override
	public Object getAllTeacherAttendance(Pageable pageable, YearMonth yearMonth) {
		LocalDate firstDayofMonth = yearMonth.atDay(1);
		
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		Page<Teacher> page = teacherRepository.findAll(pageable);
//		page.getContent().stream().map((teacher)->this.attendanceMapper.toMonthAttendance(teacher, staffAttendanceRepo.findAttendanceDateByTeacherAndAttendanceDateBetween(teacher,firstDayofMonth,endOfMonth),yearMonth)).collect(Collectors.toList());
		return null;
	}

}
