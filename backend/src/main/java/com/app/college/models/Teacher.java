package com.app.college.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	
	public Teacher(Long teacherId) {
		this.id=teacherId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String empId;

	@Column(length = 100, nullable = false)
	private String firstName;

	@Column(length = 100, nullable = false)
	private String lastName;

	@ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
//	@Column(length = 50, nullable = false)
//	private String departments;

	@Column(length = 50, nullable = false)
	private String designation;

	@Column(length = 10, nullable = false)
	private String gender;

	@Column(length = 50)
	private String degree;

	@Column(length = 13, nullable = false)
	private String mobile;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 150)
	private String address;

	@Column(length = 150, nullable = false)
	private String education;
	
	//@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;

	@Column(name = "profile_photo_filename")
	private String profilePhoto;

	private LocalDateTime profileUpdatedDate;
	
	private LocalDateTime profileCreatedAt;
	
	@Size(max = 20)
	private String username;
	
	private String spouseName;
	
	private LocalDate dob;
	
	private String bloodGroup;
	
	private String aadhar;

	@OneToMany(mappedBy = "teacher")
    private List<StaffAttendance> attendance;
}
