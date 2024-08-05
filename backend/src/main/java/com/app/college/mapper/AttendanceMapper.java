package com.app.college.mapper;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.college.models.StaffAttendance;
import com.app.college.models.Student;
import com.app.college.models.StudentAttendance;
import com.app.college.models.TimeTable;

@Component
public class AttendanceMapper {
	
	public List<StudentAttendance> toStudentAttendance(Long timeTableId,List<Long> studentIds) {
		TimeTable timeTable=new TimeTable(timeTableId);
		return studentIds.stream().map((id)-> new StudentAttendance(timeTable,id)).collect(Collectors.toList());
	}


	public Map<String, Object> toMonthAttendance(Student student,
			List<LocalDate> dates,YearMonth yearMonth) {
		
		Map<String, Object> attMap=new HashMap<>();
		
		attMap.put("StudentId", student.getId());
		
		attMap.put("rollNo", student.getRollNo());
		
		attMap.put("studentName", student.getStudentName());
		attMap.put("profilePhoto", student.getProfilePhoto());
		
   
        for(int i=1;i<=yearMonth.lengthOfMonth();i++) {
//        	LocalDate date = yearMonth.atDay(i);
        	
        	attMap.put("d"+i, false);
        }
        
        LocalDate tempDate=LocalDate.MIN;
        
        for (LocalDate date : dates) {
        	if(tempDate==date)
        		continue;
        	else
        		tempDate=date;
            attMap.put("d"+date.getDayOfMonth(), true);
        }

        return attMap;
		
	}


	public StaffAttendance toTeacherAttendance(Long timeTableId, Long teacherId) {
	    TimeTable timeTable = new TimeTable(timeTableId);
	    return Optional.ofNullable(teacherId)
	            .map(id -> new StaffAttendance(timeTable, id))
	            .orElseThrow(() -> new IllegalArgumentException("Teacher ID cannot be null"));
	}

}
