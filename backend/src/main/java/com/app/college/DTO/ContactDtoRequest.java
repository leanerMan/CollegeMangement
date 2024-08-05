package com.app.college.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactDtoRequest {

	
	private Long id;

	private String contactImageName;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 50, message = "Name must be between 2 to 100 characters")
	private String name;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "invalid email")
	private String email;
	
	@Pattern(regexp = "^\\d{10,13}$", message = "Mobile number must be numeric and have 10 to 13 digits")
	private String mobile;
	
	@NotNull(message = "Birth Date cannot be null")
	private LocalDate birthDate;
	
	@NotBlank(message = "Address cannot be blank")
	@Size(min = 2, max = 50, message = "Address must be between 2 to 200 characters")
	private String address;
	
	private String note;
}
