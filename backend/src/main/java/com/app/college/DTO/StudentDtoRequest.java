package com.app.college.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDtoRequest {

	private Long id;
	
//	@NotBlank(message = "First Name cannot be blank")
//	@Size(min = 2, max = 50, message = "First Name must be between 2 to 50 characters")
//	private String firstName;
//
//	@Size(min = 2, max = 50, message = "Last Name must be between 2 to 50 characters")
//	private String lastName;

//	@NotBlank(message = "Departments cannot be blank")
//	@Size(min = 2, max = 50, message = "Department must be between 2 to 50 characters")
	private Long departmentId;
	
	private String departmentName;

//	@NotBlank(message = "Designation cannot be blank")
//	@Size(min = 2, max = 50, message = "Designation must be between 2 to 50 characters")
//	private String designation;

	@NotBlank(message = "Gender cannot be blank")
	@Size(min = 2, max = 50, message = "Gender must be between 2 to 10 characters")
	private String gender;

	private String rollNo;
	
//	^\\d{10}(\\d{2})?$

	@Pattern(regexp = "^\\d{10,13}", message = "Contact number must be 10 or 13 digits ")
	private String mobile;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "invalid email")
	private String email;

//	@Size(min = 2, max = 100, message = "Address must be between 2 to 100 characters")
//	private String address;

//	@NotBlank(message = "Education cannot be blank")
//	@Size(min = 2, max = 50, message = "Education must be between 2 to 100 characters")
//	private String education;

//	@JsonFormat(pattern = "dd-MM-yyyy")
//	private LocalDate admissionDate;
	
//	@JsonFormat(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate admissionDate;

	private String profilePhoto;
	
	//private LocalDateTime profileCreatedAt;
	
	@NotBlank(message = "Student Name cannot be blank")
	@Size(min = 2, max = 50, message = "Student Name must be between 2 to 50 characters")
	private String studentName;
	
	
	//@Size(min = 2, max = 50, message = "Father's Name must be between 2 to 50 characters")
    private String fathersName;
	
	
	//@Size(min = 2, max = 50, message = "Mother's Name must be between 2 to 50 characters")
    private String mothersName;
   
	
    private String altMobile;
	
    //@NotBlank(message = "Course cannot be blank")
    @NotNull
	private Long courseId;
	private String courseName;
	private String courseCode;
	private String sem;

	private String stream;

	private String paper;
    
//    @JsonFormat(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    
    @JsonProperty("pPin")
    @NotBlank(message = "Permanent PIN cannot be blank")
    private String pPin;
    
    @JsonProperty("pAddress")
    @NotBlank(message = "Permanent address cannot be blank")
    @Size(min = 2, max = 100, message = "Permanent Address must be between 2 to 100 characters")
    private String pAddress;
    
    @JsonProperty("pCity")
    @Size(min = 2, max = 15, message = "Permanent city must be between 2 to 15 characters")
    @NotBlank(message = "Permanent city cannot be blank")
    private String pCity;
    
    @JsonProperty("pState")
    @Size(min = 2, max = 15, message = "Permanent Address must be between 2 to 15 characters")
    @NotBlank(message = "Permanent state cannot be blank")
    private String pState;
    
    @JsonProperty("cPin")
    private String cPin;
    
    
    //@Size(min = 2, max = 100, message = "Correspondance Address must be between 2 to 100 characters")
    @JsonProperty("cAddress")
    private String cAddress;
    
    @JsonProperty("cCity")
    private String cCity;
    
    @JsonProperty("cState")
    private String cState;
    
    @NotBlank(message = "Category cannot be blank")
    private String category;
    
    private String college;
    
    private String maritalStatus;
    
    @Size(min = 12, max = 12, message = "Aadhar number must be 12 characters")
    @NotBlank(message = "Aadhar number cannot be blank")
    private String aadhaarNo;

    @Size(min = 2, max = 3, message = "Blood group must be between 2 to 3 characters")
    @NotBlank(message = "Blood group cannot be blank")
    private String bloodGroup;
	
	private String profileSignature;
	
	//private MultipartFile profilePhoto;


}
