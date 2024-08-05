package com.app.college.DTO;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDto {

		private long id;
	
		@NotBlank(message = "department cannot be blank")
		@Size(min = 2, max = 50, message = "department must be between 2 to 50 characters")
	    private String department;

		@NotBlank(message = "headOfDepartment cannot be blank")
		@Size(min = 2, max = 50, message = "headOfDepartment must be between 2 to 50 characters")
	    private String headOfDepartment;

	    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 13 digits & be numric")
	    private String phone;

	    @Email(message = "invalid email")
	    private String email;

	    private LocalDate departmentStartDate;

	    //@Size(max = 15, message = "Student length capacity must be between 1 and 15")
	    private int studentCapacity;

	    @Size(max = 200)
	    private String details;
	    
	    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
		private LocalDateTime departmentGenDate;
}
