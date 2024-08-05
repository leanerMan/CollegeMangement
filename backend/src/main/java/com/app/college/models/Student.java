package com.app.college.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")/* ,uniqueConstraints = {@UniqueConstraint(columnNames = {"email","mobile"})} */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	public Student(Long studentId) {
		this.id=studentId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

//	@Column(length = 50, nullable = false)
//	private String designation;

	@Column(length = 10, nullable = false)
	private String gender;

	@Column(length = 30)
	private String rollNo;

	@Column(length = 13, nullable = false,unique = true)
	private String mobile;

	@Column(length = 50, nullable = false,unique = true)
	private String email;

//	@Column(length = 150)
//	private String address;

//	@Column(length = 150, nullable = false)
//	private String education;

	@Column(name="student_name",length = 100, nullable = false)
	private String studentName;
	
	@Column(name="fathers_name",length = 100)
    private String fathersName;
	
	@Column(name="mothers_name",length = 100)
    private String mothersName;
   
	@Column(name="alternate_mobile",length = 13)
    private String altMobile;
	
	@ManyToOne
	private Course course;

	private String sem;
	
	private String stream;
	
	private String paper;	
    
    @Column(name="dob",nullable = false)
    private LocalDate dob;
    
    @JsonProperty("pPin")
    @Column(name="p_pin",length = 8)
    private String pPin;
    
    @JsonProperty("pAddress")
    @Column(name="p_address",length = 200)
    private String pAddress;
    
    @JsonProperty("pCity")
    @Column(name="p_city",length = 50)
    private String pCity;
    
    @JsonProperty("pState")
    @Column(name="p_state",length = 50)
    private String pState;
    
    @JsonProperty("cPin")
    @Column(name="c_pin",length = 8)
    private String cPin;
    
    @JsonProperty("cAddress")
    @Column(name="c_address",length = 200)
    private String cAddress;
    
    @JsonProperty("cCity")
    @Column(name="c_city",length = 50)
    private String cCity;
    
    @JsonProperty("cState")
    @Column(name="c_state",length = 50)
    private String cState;
    
    @Column(name="category",length = 50,nullable = false)
    private String category;
    
    @Column(name="marital_status",length = 10)
    private String maritalStatus;
    
    @Column(name="aadhaar_no",length = 12,nullable = false)
    private String aadhaarNo;
    
    @Column(name="college",length = 100)
    private String college;
    
    @Column(nullable = false)
//    @JsonFormat(pattern="dd-MM-yyyy")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate admissionDate;
    
    @Column(name = "profile_photo_filename")
	private String profilePhoto;
	
	@Column(name = "profile_signature_filename")
	private String profileSignature;
    
    private LocalDateTime profileCreatedAt;
    
    private LocalDateTime profileUpdatedDate;
   
    @Column(name = "blood_group",length=5)
    private String bloodGroup;
    
    @OneToMany(mappedBy = "student")
    private List<StudentAttendance> attendance;

	@Column(length = 30)
    private String studentId;
	
	@Size(max = 20)
	private String username;


}
