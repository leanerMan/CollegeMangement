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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffAttendance {

    public StaffAttendance(TimeTable timeTable, Long teacherId) {
		this.timeTable=timeTable;
		this.teacher= new Teacher(teacherId);
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Teacher teacher;

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

