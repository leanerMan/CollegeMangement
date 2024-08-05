package com.app.college.service.impl;

import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.OtpData;
import com.app.college.models.Role;
import com.app.college.models.Student;
import com.app.college.models.Teacher;
import com.app.college.models.User;
import com.app.college.payload.request.LoginRequest;
import com.app.college.repository.OptRepository;
import com.app.college.repository.RoleRepository;
import com.app.college.repository.StudentRepository;
import com.app.college.repository.TeacherRepository;
import com.app.college.repository.UserRepository;
import com.app.college.service.OtpService;
import com.app.college.util.DateTimeGenerator;
import com.app.college.util.WhatsAppMessageRequest;

import jakarta.transaction.Transactional;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private OptRepository otpRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Boolean generateOtp(String contactPhone, String role) {

		Boolean validNumber = switch (role) {
		case "student":
			yield studentRepo.existsByMobile(contactPhone);
		case "teacher":
			yield teacherRepo.existsByMobile(contactPhone);
		case "admin":
			yield false;
		default:
			throw new IllegalArgumentException("Unexpected value: " + role);
		};

		if (validNumber) {
			String generatedOtp = generateOTP();
			OtpData otp = otpRepo.save(
					new OtpData(contactPhone, generatedOtp, false, DateTimeGenerator.getAutoLocalDateTime(), role));
			if (otp != null) {
				String apiUrl = "https://wa.intractly.com/api/send";
				final String instance_id = "65E17C20D37B1";
				final String access_token = "65e1791dc19e9";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				headers.set("Authorization", "Bearer " + access_token);

				WhatsAppMessageRequest messageRequest = new WhatsAppMessageRequest();
				messageRequest.setNumber("91" + contactPhone);
				messageRequest.setInstance_id(instance_id);
				messageRequest.setAccess_token(access_token);

				String msg = "OTP is " + generatedOtp + " for College Login. Do not share OTP for security reasons";
				messageRequest.setMessage(msg);
				messageRequest.setType("text");
				HttpEntity<WhatsAppMessageRequest> requestEntity = new HttpEntity<>(messageRequest, headers);
				ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
				return response.getBody().contains(msg);
			}
		}
		throw new ConfigDataNotFound("Number not found");

	}

	@Transactional
	public LoginRequest varifyOtp(String contactPhone, String provideOtp) throws InvalidKeyException {
		OtpData otpData = otpRepo.findById(contactPhone).orElseThrow(() -> new ConfigDataNotFound("Number Mismatch"));

		LoginRequest loginRequest = null;
		User user = null;
		String tempPassword = "default";
		if ( otpData.getOtp() != null && !otpData.getVerified()
				&& otpData.getOtp().equals(provideOtp)) {
			
	
			Duration duration = Duration.between(otpData.getTime(), DateTimeGenerator.getAutoLocalDateTime());
			if (duration.getSeconds() <= 60) {
				if (otpData.getRole().equalsIgnoreCase("student")) {
					Student student = studentRepo.findByMobile(contactPhone)
							.orElseThrow(() -> new ConfigDataNotFound("Number Mismatch"));
					user = userRepository.findByUsername(student.getUsername()).orElse(null);
					if(user==null) {
						user=this.makeUser(student.getUsername(),student.getEmail(),student.getStudentId(),student.getRollNo(), tempPassword, otpData.getRole());
					}
					tempPassword = "COLST" + contactPhone.substring(5, 9);
//					if (user != null) {
						user.setPassword(encoder.encode(tempPassword));
						loginRequest = new LoginRequest();
						loginRequest.setPassword(tempPassword);
						loginRequest.setUsername(user.getUsername());

//					} else {
//						user = this.makeUser(contactPhone,student.getEmail(),student.getStudentId(),student.getRollNo(), tempPassword, otpData.getRole());
//
//					}
				}
				if (otpData.getRole().equalsIgnoreCase("teacher")) {
					Teacher teacher = teacherRepo.findByMobile(contactPhone)
							.orElseThrow(() -> new ConfigDataNotFound("Number Mismatch"));
					user = userRepository.findByUsername(teacher.getUsername()).orElse(null);
					if(user==null) {
						user=this.makeUser(contactPhone,teacher.getEmail(),teacher.getEmpId(),null, tempPassword, otpData.getRole());
					}
					tempPassword = "COLTR" + contactPhone.substring(5, 9);
//					if (user != null) {
						user.setPassword(encoder.encode(tempPassword));

//					} else {
//						user = this.makeUser(teacher.getUsername(),teacher.getEmail(),teacher.getEmpId(),null, tempPassword, otpData.getRole());
//
//					}

				}
				if (user != null) {
					loginRequest = new LoginRequest();
					loginRequest.setPassword(tempPassword);
					loginRequest.setUsername(user.getUsername());
					otpData.setVerified(true);
					otpRepo.save(otpData);
					userRepository.save(user);

				}
			}else {
				throw new ConfigDataNotFound("opt expired");
			}
			

		} else {
			throw new InvalidKeyException("Invalid Otp");
		}
		return loginRequest;
	}

	private User makeUser(String contactPhone, String email, String tempPassword,String userId,String rollno, String role) {
		User user = new User(contactPhone, email, encoder.encode(tempPassword), userId, rollno);

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(role)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);
		return userRepository.save(user);
	}

	private String generateOTP() {
		int otpLength = 5;
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		for (int i = 1; i < otpLength; i++) {
			int digit = random.nextInt(9) + 1;
			otp.append(digit);
		}

		return otp.toString();
	}

}
