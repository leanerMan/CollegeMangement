package com.app.college.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StaffDtoRequest {
	
	private Long id;
	
	@NotBlank(message = "First Name cannot be blank")
	@Size(min = 2, max = 50, message = "First Name must be between 2 to 50 characters")
	private String firstName;

	@Size(min = 2, max = 50, message = "Last Name must be between 2 to 50 characters")
	private String lastName;

	@NotBlank(message = "Departments cannot be blank")
	@Size(min = 2, max = 50, message = "Department must be between 2 to 50 characters")
	private String departments;

	@NotBlank(message = "Designation cannot be blank")
	@Size(min = 2, max = 50, message = "Designation must be between 2 to 50 characters")
	private String designation;

	@NotBlank(message = "Gender cannot be blank")
	@Size(min = 2, max = 50, message = "Gender must be between 2 to 10 characters")
	private String gender;


	@Pattern(regexp = "^\\d{10,13}$", message = "Contact number must be numeric and have 10 to 13 digits")
	private String mobile;

	@Email(message = "invalid email")
	private String email;

	@Size(min = 2, max = 100, message = "Address must be between 2 to 100 characters")
	private String address;

	@NotBlank(message = "Education cannot be blank")
	@Size(min = 2, max = 50, message = "Education must be between 2 to 100 characters")
	private String education;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;

	private String profilePhoto;

	
}
