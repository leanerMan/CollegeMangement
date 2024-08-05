package com.app.college.models;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {
	
	public TimeTable(Long timeTableId) {
		this.id=timeTableId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Course course;

	private String sem;
	
	private String stream;
	
	private String paper;	
	
	@ManyToOne
	private ClassRoom room;
	
	@Column(nullable = false)
	private LocalTime startTime;
	
	@Column(nullable = false)
	private LocalTime endTime;
	
	@ManyToOne
	private Teacher teacher;
	
	@ManyToOne
	private Department department;

	private String day;
	
	private String theme;

	
	
}
