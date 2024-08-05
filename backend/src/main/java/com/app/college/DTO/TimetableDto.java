package com.app.college.DTO;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableDto {

	private Long id;

	private Long courseId;
	private String courseName;
	private String courseCode;
	private String sem;

	private String stream;

	private String paper;

	private Long roomId;
	private String roomNo;
	private String block;
	private String building;
	private String floor;

	private LocalTime startTime;
	private LocalTime endTime;

	private Long teacherId;
	private String teacherEmpId;
	private String teacherFirstName;
	private String teacherLastName;
	private String day;

	private String theme;

}
