package com.app.college.service;

import java.util.List;
import java.util.Map;

import com.app.college.DTO.StuExamView;

public interface StudentViewService {

	Map<String, Object> getStuAdmidCardUgPgById(String studentId);

	Map<String, Object> getStuResultById(String rollno);

	List<StuExamView> getStuExamById(String rollno);

	Object getStuAdmidCardUgPgByIdAndSem(String studentId, String sem);
	
	Object getStuResultByIdAndSem(String rollno, String sem);

}
