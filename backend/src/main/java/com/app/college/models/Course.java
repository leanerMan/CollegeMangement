package com.app.college.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "course_name", length = 50, nullable = false)
	private String courseName;
	
	@Column(name = "course_code", length = 50, nullable = false)
	private String courseCode;
	
	@Column(name = "course_time_length", length = 20, nullable = false)
	private String courseTimeLength;
	

	@Column(name = "start_date")
	private LocalDate startDate;
	
//	@Column(name = "professor_name", length = 50, nullable = false)
//	private String professorName;
	
	@ManyToMany
	private List<Teacher> teacher;
	
	@ManyToOne
	private Department department;
	
	@Column(name = "sem_code", length = 2)
	private int semCode;
	
	@Column(name = "contact_number", length = 50, nullable = false)
	private String contactNumber;
	
	@Column(name = "course_details", length = 100)
	private String courseDetails;
	
	@Column(name = "course_status", length = 2)
	private String courseStatus;

	@JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
	@Column(name = "course_gen_date")
	private LocalDateTime courseGenDate;
	
	 @PrePersist
	    public void prePersist() {
	        if (this.courseGenDate == null) {
	            this.courseGenDate = LocalDateTime.now();
	        }
	    }

	public Course(Long courseId) {
		this.id=courseId;
	}
}
