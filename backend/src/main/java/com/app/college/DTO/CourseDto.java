package com.app.college.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CourseDto {
	
	private Long id;

    private String courseName;

    private String courseCode;

    private String courseTimeLength;

    private LocalDate startDate;

//    @NotBlank(message = "professorName cannot be blank")
//    @Size(min = 2, max = 50, message = "professorName must be between 2 to 50 characters")
//    private String professorName;
    
	private List<TeacherDtoRequest> teacher;
	
	private Long departmentId;
	private String department;
	private String headOfDepartment;

	private int semCode;

    private String contactNumber;

    private String courseDetails;
    
    private String courseStatus;

	private LocalDateTime courseGenDate;
}
