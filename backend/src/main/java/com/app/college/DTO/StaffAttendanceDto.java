package com.app.college.DTO;

import lombok.Data;

@Data
public class StaffAttendanceDto {

	 	private Long id;

	    private Long timeTableId;
	    
	    private Long teacherId;
	    
	    private String details;
}
