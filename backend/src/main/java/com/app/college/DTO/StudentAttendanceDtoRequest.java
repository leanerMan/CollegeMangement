package com.app.college.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendanceDtoRequest {

	private Long id;

	private Long timeTableId;
	
	private List<Long> studentIds;

	private String details;

}
