package com.app.college.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students_attendance")
public class StudentAttendance {

	public StudentAttendance(TimeTable timeTable, Long studentId) {
		this.timeTable=timeTable;
		this.student=new Student(studentId);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Student student;

	private LocalDate attendanceDate= LocalDate.now();
	
	private LocalTime attendanceTime= LocalTime.now();
	
	@ManyToOne
	private TimeTable timeTable;

	@Column(name = "in_time")
	private LocalDateTime inTime;

	@Column(name = "out_time")
	private LocalDateTime outTime;

	@Column(name = "details")
	private String details;


}
