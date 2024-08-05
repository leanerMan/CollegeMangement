package com.app.college.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.app.college.DTO.StudentAttendanceDtoRequest;
import com.app.college.DTO.StudentDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.AttendanceMapper;
import com.app.college.mapper.StudentMapper;
import com.app.college.models.Student;
import com.app.college.models.StudentAttendance;
import com.app.college.payload.response.MonthAttendance;
import com.app.college.repository.StudentAttendanceRepository;
import com.app.college.repository.StudentRepository;
import com.app.college.service.StudentService;
import com.app.college.util.DateTimeGenerator;

import jakarta.validation.Valid;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	AttendanceMapper attendanceMapper;
	
	@Autowired
	StudentAttendanceRepository attendanceRepo;

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
	private String bucketName;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentDtoRequest updateStudentById(Long id,StudentDtoRequest studentDtoRequest, MultipartFile profilePhotoFile,
			MultipartFile profileSignatureFile) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("No Student found with id " + id));

		BeanUtils.copyProperties(studentDtoRequest, student, "profilePhoto");

		try {
			// Update profile photo if provided
			if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
				File tempPhotoFile = File.createTempFile("temp_photo", null);
				tempPhotoFile.deleteOnExit();

				profilePhotoFile.transferTo(tempPhotoFile);

				File photoFile = tempPhotoFile;

				ObjectMetadata photoMetadata = new ObjectMetadata();
				photoMetadata.setContentType(profilePhotoFile.getContentType());

				String photoFilename = studentDtoRequest.getProfilePhoto();
				String photoKey = "students/" + photoFilename;
				student.setProfilePhoto(photoFilename);

				PutObjectRequest photoPutObjectRequest = new PutObjectRequest(bucketName, photoKey, photoFile);
				photoPutObjectRequest.setMetadata(photoMetadata);

				student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
				studentRepository.save(student);

				s3Client.putObject(photoPutObjectRequest);

				photoFile.delete();
			}

			// Update profile signature if provided
			if (profileSignatureFile != null && !profileSignatureFile.isEmpty()) {
				File tempSignatureFile = File.createTempFile("temp_signature", null);
				tempSignatureFile.deleteOnExit();

				profileSignatureFile.transferTo(tempSignatureFile);

				File signatureFile = tempSignatureFile;

				ObjectMetadata signatureMetadata = new ObjectMetadata();
				signatureMetadata.setContentType(profileSignatureFile.getContentType());

				String signatureFilename = studentDtoRequest.getProfileSignature();
				String signatureKey = "students/" + signatureFilename;
				student.setProfileSignature(signatureFilename);

				PutObjectRequest signaturePutObjectRequest = new PutObjectRequest(bucketName, signatureKey,
						signatureFile);
				signaturePutObjectRequest.setMetadata(signatureMetadata);

				student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
				studentRepository.save(student);

				s3Client.putObject(signaturePutObjectRequest);

				signatureFile.delete();
			}

			// Update student entity
			student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
			studentRepository.save(student);

		} catch (IOException e) {
			throw new RuntimeException("Error occurred while saving profile photo or signature!!!", e);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentMapper.toDto(student);
	}

//	@Override
//	public StudentDtoRequest updateStudentById(StudentDtoRequest studentDtoRequest, MultipartFile profilePhotoFile,MultipartFile profileSignatureFile) {
//		Student student = studentRepository.findById(studentDtoRequest.getId())
//				.orElseThrow(() -> new ConfigDataNotFound("No Student found with id " + studentDtoRequest.getId()));
//
//		BeanUtils.copyProperties(studentDtoRequest, student, "profilePhoto");
//		Student a1 = null;
////            LocalDateTime lc=LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
//		try {
//			// Check if the file is not empty
//			if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
//				// Create a temporary file
//				File tempFile = File.createTempFile("temp", null);
//				tempFile.deleteOnExit();
//
//				// Transfer the content of MultipartFile to the temporary file
//				profilePhotoFile.transferTo(tempFile);
//
//				// Create a File object for the uploaded file
//				File imageFile = tempFile;
//
//				// Set up metadata for the S3 object
//				ObjectMetadata metadata = new ObjectMetadata();
//				metadata.setContentType(profilePhotoFile.getContentType());
////                int lastDotIndex = profilePhotoFile.getOriginalFilename().lastIndexOf('.');
////                String fileExtension = lastDotIndex == -1 ? "" : profilePhotoFile.getOriginalFilename().substring(lastDotIndex + 1);
////                String filename = "stu"+ "_"+System.currentTimeMillis()+"profile."+ fileExtension;
//				String filename = studentDtoRequest.getProfilePhoto();
//				// Specify the key for the S3 object (e.g., images/my_image.jpg)
//				String key = "students/" + filename;
//				student.setProfilePhoto(filename);
//				// Create a PutObjectRequest to upload the image to S3
//				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
//				putObjectRequest.setMetadata(metadata);
//
//				student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
//				a1 = studentRepository.save(student);
//				if (a1 != null) {
//					// Upload the image to S3
//					s3Client.putObject(putObjectRequest);
//				}
//				// Optionally, you can delete the local file after uploading to S3
//				imageFile.delete();
//
//				// Redirect to a success page or return a success message
//			} else {
//				student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
//				a1 = studentRepository.save(student);
//			}
//		} catch (IOException e) {
//			throw new RuntimeException("Error occurred while saving profile photo!!!", e);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return studentMapper.toDto(student);
//
//	}

	@Override
	public String deleteStudentById(Long id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return "Student deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Student found with id: " + id);
	}

	@Override
	public StudentDtoRequest getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("No Student found with id: " + id));
		return studentMapper.toDto(student);
	}

	@Override
	public List<StudentDtoRequest> getAllStudent() {
		List<Student> students = studentRepository.findAll();
		List<StudentDtoRequest> newDtoList = new ArrayList<>();
		for (Student student : students) {
			newDtoList.add(studentMapper.toDto(student));
		}
		return newDtoList;
	}

	@Override
	public StudentDtoRequest createStudentsWithProfilePhoto(@Valid StudentDtoRequest studentDtoRequest,
			MultipartFile profilePhotoFile, MultipartFile profileSignatureFile) {

		Student student = studentRepository.save(
				studentMapper.toEntityWithProfilePhoto(studentDtoRequest, profilePhotoFile, profileSignatureFile));
		return studentMapper.toDto(student);
	}

	@Override
	public Page<StudentDtoRequest> getAllStudent(Pageable pageable) {
		Page<Student> students = studentRepository.findAll(pageable);
		List<StudentDtoRequest> newDtoList = new ArrayList<>();
		for (Student student : students.getContent()) {
			newDtoList.add(studentMapper.toDto(student));
		}
		return new PageImpl<>(newDtoList, pageable, students.getTotalElements());
	}

	@Override
	public Page<Student> searchStudents(Pageable pageable, String keyword) {
		String keywordLowerCase = keyword != null ? keyword.toLowerCase() : null;
		Page<Student> students = studentRepository.searchStudents(pageable, keywordLowerCase);
		return students;
	}

	@Override
	public List<StudentAttendanceDtoRequest> getAttendaceByStudentId(Long studentId) {
		List<StudentAttendance> studentsAttendance = studentRepository.findAllAttendanceById(studentId);
		
		List<StudentAttendanceDtoRequest> newDtoList = new ArrayList<>();
		for (StudentAttendance stuAtt : studentsAttendance) {
			newDtoList.add(mapper.map(stuAtt, StudentAttendanceDtoRequest.class));
		}
		return newDtoList;
//		return studentsAttendance.stream().map(element->mapper.map(studentsAttendance, StudentAttendanceDtoRequest.class)).collect(Collectors.toList());
	}

	@Override
	public StudentDtoRequest getStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username)
				.orElseThrow(() -> new ConfigDataNotFound("No Student found with id: " + username));
		return studentMapper.toDto(student);
	}

	@Override
	public Object getAllStudentAttendance(Pageable pageable,YearMonth yearMonth) {
		// Get the first day of the month
		LocalDate firstDayOfMonth = yearMonth.atDay(1);

		// Get the last day of the month
		LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
		Page<Student> studentPage=studentRepository.findAll(pageable);
		List<Map<String, Object>> list= studentPage.getContent().stream().map((student)->this.attendanceMapper.toMonthAttendance(student,attendanceRepo.findAttendanceDateByStudentAndAttendanceDateBetween(student,firstDayOfMonth,lastDayOfMonth),yearMonth)).collect(Collectors.toList());
		 return new PageImpl<>(list, pageable, studentPage.getTotalElements());
//		return studentPage;
	}
	


}
