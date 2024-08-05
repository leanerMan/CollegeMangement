package com.app.college.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherDtoRequest {
	
	private Long id;

	@NotBlank(message = "First Name cannot be blank")
	@Size(min = 2, max = 50, message = "First Name must be between 2 to 50 characters")
	private String firstName;

	//@Size(min = 2, max = 50, message = "Last Name must be between 2 to 50 characters")
	private String lastName;

	private Long departmentId;
	
	private String departmentName;

	@NotBlank(message = "Designation cannot be blank")
	@Size(min = 2, max = 50, message = "Designation must be between 2 to 50 characters")
	private String designation;

	@NotBlank(message = "Gender cannot be blank")
	@Size(min = 2, max = 10, message = "Gender must be between 2 to 10 characters")
	private String gender;

	//@NotBlank(message = "Degree cannot be blank")
	//@Size(min = 2, max = 50, message = "Degree must be between 2 to 10 characters")
	private String degree;

	@Pattern(regexp = "^\\d{10}(\\d{2})?$", message = "Contact number must be 10 or 13 digits ")
	private String mobile;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "invalid email")
	private String email;

	@Size(min = 2, max = 100, message = "Address must be between 2 to 100 characters")
	private String address;

	@NotBlank(message = "Education cannot be blank")
	@Size(min = 2, max = 50, message = "Education must be between 2 to 100 characters")
	private String education;

	//@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;

	private String profilePhoto;
	
	//private LocalDateTime profileCreatedAt;


}
