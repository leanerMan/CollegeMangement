package com.app.college.controllers;

import java.security.InvalidKeyException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.college.models.Role;
import com.app.college.models.User;
import com.app.college.payload.request.LoginRequest;
import com.app.college.payload.request.SignupRequest;
import com.app.college.payload.response.ApiResponse;
import com.app.college.payload.response.JwtResponse;
import com.app.college.payload.response.MessageResponse;
import com.app.college.repository.RoleRepository;
import com.app.college.repository.UserRepository;
import com.app.college.security.jwt.JwtUtils;
import com.app.college.security.services.UserDetailsImpl;
import com.app.college.service.OtpService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private OtpService otpService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtToken(authentication, loginRequest.getType());

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName("user")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			roles = strRoles.stream()
					.map(role -> roleRepository.findByName(role)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found.")))
					.collect(Collectors.toSet());

		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/generate-otp")
	public ResponseEntity<ApiResponse> generateOpt(@RequestBody Map<String, String> requestBody) {
	       String number = requestBody.get("number");
	       String role = requestBody.get("role");

		if (otpService.generateOtp(number, role)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", "OTP sent"), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Fail!", "Fail to send OTP"),
					HttpStatus.CREATED);
		}

	}

	@PostMapping("/verify-otp")
	public ResponseEntity<ApiResponse> verifyOtp(@RequestBody Map<String, String> requestBody)
			throws InvalidKeyException {
		
		   String number = requestBody.get("number");
	       String otp = requestBody.get("otp");
		LoginRequest login = otpService.varifyOtp(number, otp);

		if (login != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Success!", login), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Fail!", null), HttpStatus.CREATED);
		}
	}
}
