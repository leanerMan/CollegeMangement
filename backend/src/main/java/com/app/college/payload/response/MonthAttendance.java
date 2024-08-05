package com.app.college.payload.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthAttendance {
	
	private Integer dayCount;
	
	Map<String,Object> attendace;

}
