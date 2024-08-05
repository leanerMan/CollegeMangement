package com.app.college.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.college.repository.LibraryRepository;
import com.app.college.service.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService{

	private LibraryRepository libraryRepository;

	public DashBoardServiceImpl(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@Override
	public Map<String, Object> getMisDashboardData() {
	    Long[][] misDashboardData = libraryRepository.getMisDashboardData();

	    Long totalOldStudents = misDashboardData[0][0];
	    Long totalNewStudents = misDashboardData[0][1];
	    Long totalOldCourses = misDashboardData[0][2];
	    Long totalNewCourses = misDashboardData[0][3];
	    Long totalOldTeachers = misDashboardData[0][4];
	    Long totalNewTeachers = misDashboardData[0][5];
	    Long totalOldDepartments = misDashboardData[0][6];
	    Long totalNewDepartments = misDashboardData[0][7];

	    double totalStudents = totalOldStudents + totalNewStudents;
	    double oldStudentsPercentage = (totalOldStudents * 100.0) / totalStudents;
	    double newStudentsPercentage = (totalNewStudents * 100.0) / totalStudents;

	    double prcntgIncrsInCrrntYrStudents = ((double) (totalNewStudents - totalOldStudents) / totalOldStudents) * 100;
	    long totalCourses = totalOldCourses + totalNewCourses;
	    double prcntgIncrsInCrrntYrCourses = ((double) (totalNewCourses - totalOldCourses) / totalOldCourses) * 100;
	    long totalTeachers = totalOldTeachers + totalNewTeachers;
	    double prcntgIncrsInCrrntYrTeachers = ((double) (totalNewTeachers - totalOldTeachers) / totalOldTeachers) * 100;
	    long totalDepartments = totalOldDepartments + totalNewDepartments;
	    double prcntgIncrsInCrrntYrDepartments = ((double) (totalNewDepartments - totalOldDepartments) / totalOldDepartments) * 100;

	    List<String> labels = Arrays.asList(
	        "Total Students", "% increase in this year of Students",
	        "Total Courses", "% increase in this year of Courses",
	        "Total Teachers", "% increase in this year of Teachers",
	        "Total Departments", "% increase in this year of Departments"
	    );
	    List<Object> dataValues = Arrays.asList(
	        totalStudents, prcntgIncrsInCrrntYrStudents,
	        totalCourses, prcntgIncrsInCrrntYrCourses,
	        totalTeachers, prcntgIncrsInCrrntYrTeachers,
	        totalDepartments, prcntgIncrsInCrrntYrDepartments
	    );

	    Map<String, Object> dashboardDataMap = new HashMap<>();
	    dashboardDataMap.put("labels", labels);
	    dashboardDataMap.put("data", dataValues);

	    List<String> label1 = Arrays.asList("Old Students Percentage", "New Students Percentage");
	    List<Object> dataValue1 = Arrays.asList(oldStudentsPercentage, newStudentsPercentage);
	    Map<String, Object> studentsSurvey = new HashMap<>();
	    studentsSurvey.put("labels", label1);
	    studentsSurvey.put("data", dataValue1);

	    Map<String, Object> dashboardData = new HashMap<>();
	    dashboardData.put("dashboardDataMap", dashboardDataMap);
	    dashboardData.put("studentsSurvey", studentsSurvey);

	    return dashboardData;
	}

}
