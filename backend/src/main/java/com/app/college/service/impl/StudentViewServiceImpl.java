package com.app.college.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.college.DTO.StuAdmidCardUgPgView;
import com.app.college.DTO.StuExamView;
import com.app.college.DTO.StuResultView;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.repository.NoticeRepository;
import com.app.college.service.StudentViewService;

@Service
public class StudentViewServiceImpl implements StudentViewService {

	private NoticeRepository noticeRepo;

	public StudentViewServiceImpl(NoticeRepository noticeRepo) {
		this.noticeRepo = noticeRepo;
	}

	@Override
	public Map<String, Object> getStuAdmidCardUgPgById(String studentId) {
		List<StuAdmidCardUgPgView> stuAdmidCardUgPgById = noticeRepo.getStuAdmidCardUgPgById(studentId);
		Map<String, Object> resultList = new HashMap<>();

		if (stuAdmidCardUgPgById.isEmpty()) {
			throw new ConfigDataNotFound("No admission card data found for the student ID: " + studentId);
		}

		Map<String, Object> headerMap = new HashMap<>();
		StuAdmidCardUgPgView firstAdmitCard = stuAdmidCardUgPgById.get(0);
		headerMap.put("Heldmonth", firstAdmitCard.getHeldmonth());
		headerMap.put("Heldyear", firstAdmitCard.getHeldyear());
		headerMap.put("ExamStartDate", firstAdmitCard.getExamstart_date());
		headerMap.put("Course", firstAdmitCard.getCourse());
		headerMap.put("StudentId", firstAdmitCard.getStudent_id());
		headerMap.put("rollNo", firstAdmitCard.getRoll_no());
		headerMap.put("regNo", firstAdmitCard.getRegno());
		headerMap.put("collegeName", firstAdmitCard.getCollege_name());
		headerMap.put("collegeCode", firstAdmitCard.getCollege_code());
		headerMap.put("ses", firstAdmitCard.getSes());
		resultList.put("header", headerMap);

		List<Map<String, Object>> bodyList = new ArrayList<>();
		for (StuAdmidCardUgPgView admitCard : stuAdmidCardUgPgById) {
			Map<String, Object> bodyMap = new HashMap<>();
			bodyMap.put("rollNo", admitCard.getRoll_no());
			bodyMap.put("regNo", admitCard.getRegno());
			bodyMap.put("regYear", admitCard.getRegyear());
			bodyMap.put("ansIdRno", admitCard.getAnsidrno());
			bodyMap.put("examYear", admitCard.getExamyear());
			bodyMap.put("examStartDate", admitCard.getExamstart_date());
			bodyMap.put("sName", admitCard.getSname());
			bodyMap.put("dob", admitCard.getDob());
			bodyMap.put("profileImage", admitCard.getProfile_image());
			bodyMap.put("signatureImage", admitCard.getSignature_image());
			bodyMap.put("collegeCode", admitCard.getCollege_code());
			bodyMap.put("collegeName", admitCard.getCollege_name());
			bodyMap.put("centerCode", admitCard.getCenter_code());
			bodyMap.put("centerName", admitCard.getCenter_name());
			bodyMap.put("ses", admitCard.getSes());
			bodyMap.put("sem", admitCard.getSem());
			bodyMap.put("course", admitCard.getCourse());
			bodyMap.put("courseType", admitCard.getCourse_type());
			bodyMap.put("faculty", admitCard.getFaculty());
			bodyMap.put("fatherName", admitCard.getFname());
			bodyMap.put("studentType", admitCard.getStudent_type());
			bodyMap.put("examType", admitCard.getExam_type());
			bodyMap.put("examId", admitCard.getExam_id());
			bodyMap.put("eId", admitCard.getE_id());

			List<Map<String, String>> paperList = new ArrayList<>();
			addPaperIfNotNull(paperList, admitCard.getSub1_paper1_code(), admitCard.getExam_paper1(),
					admitCard.getPaper1(), admitCard.getPaper1_date());
			addPaperIfNotNull(paperList, admitCard.getSub2_paper1_code(), admitCard.getExam_paper2(),
					admitCard.getPaper2(), admitCard.getPaper2_date());
			addPaperIfNotNull(paperList, admitCard.getSub3_paper1_code(), admitCard.getEam_paper3(),
					admitCard.getPaper3(), admitCard.getPaper3_date());
			addPaperIfNotNull(paperList, admitCard.getSub4_paper1_code(), admitCard.getExam_paper4(),
					admitCard.getPaper4(), admitCard.getPaper4_date());
			addPaperIfNotNull(paperList, admitCard.getSub5_paper1_code(), admitCard.getExam_paper5(),
					admitCard.getPaper5(), admitCard.getPaper5_date());
			addPaperIfNotNull(paperList, admitCard.getSub6_paper1_code(), admitCard.getExam_paper6(),
					admitCard.getPaper6(), admitCard.getPaper6_date());
			addPaperIfNotNull(paperList, admitCard.getSub7_paper1_code(), admitCard.getExam_paper7(),
					admitCard.getPaper7(), admitCard.getPaper7_date());
			addPaperIfNotNull(paperList, admitCard.getSub8_paper1_code(), admitCard.getExam_paper8(),
					admitCard.getPaper8(), admitCard.getPaper8_date());

			bodyMap.put("papers", paperList);
			bodyList.add(bodyMap);
		}

		resultList.put("body", bodyList);
		return resultList;
	}

	private void addPaperIfNotNull(List<Map<String, String>> paperList, String subPaperCode, String codePaper,
			String paper, String paperDate) {
		if (subPaperCode != null) {
			Map<String, String> paperMap = new HashMap<>();
			paperMap.put("paperCode", subPaperCode);
			paperMap.put("codePaper", codePaper);
			paperMap.put("paperName", paper);
			paperMap.put("paperDate", paperDate);
			paperList.add(paperMap);
		}
	}

	@Override
	public Object getStuAdmidCardUgPgByIdAndSem(String studentId, String sem) {
		StuAdmidCardUgPgView admitCard = noticeRepo.getStuAdmidCardUgPgByIdAndSem(studentId, sem);
		if (admitCard == null) {
			throw new ConfigDataNotFound("No Data found with sem " + sem);
		}
		Map<String, Object> bodyMap = new HashMap<>();
		bodyMap.put("rollNo", admitCard.getRoll_no());
		bodyMap.put("regNo", admitCard.getRegno());
		bodyMap.put("regYear", admitCard.getRegyear());
		bodyMap.put("ansIdRno", admitCard.getAnsidrno());
		bodyMap.put("examYear", admitCard.getExamyear());
		bodyMap.put("sName", admitCard.getSname());
		bodyMap.put("dob", admitCard.getDob());
		bodyMap.put("profileImage", admitCard.getProfile_image());
		bodyMap.put("signatureImage", admitCard.getSignature_image());
		bodyMap.put("collegeCode", admitCard.getCollege_code());
		bodyMap.put("collegeName", admitCard.getCollege_name());
		bodyMap.put("centerCode", admitCard.getCenter_code());
		bodyMap.put("centerName", admitCard.getCenter_name());
		bodyMap.put("ses", admitCard.getSes());
		bodyMap.put("sem", admitCard.getSem());
		bodyMap.put("course", admitCard.getCourse());
		bodyMap.put("courseType", admitCard.getCourse_type());
		bodyMap.put("faculty", admitCard.getFaculty());
		bodyMap.put("ExamStartDate", admitCard.getExamstart_date());
		bodyMap.put("Heldmonth", admitCard.getHeldmonth());
		bodyMap.put("Heldyear", admitCard.getHeldyear());
		bodyMap.put("fatherName", admitCard.getFname());
		bodyMap.put("studentType", admitCard.getStudent_type());
		bodyMap.put("examType", admitCard.getExam_type());
		bodyMap.put("semInWord", getSemesterName(admitCard.getSem()));
		bodyMap.put("examId", admitCard.getExam_id());
		bodyMap.put("eId", admitCard.getE_id());

		List<Map<String, String>> paperList = new ArrayList<>();
		addPaperIfNotNull(paperList, admitCard.getSub1_paper1_code(), admitCard.getExam_paper1(), admitCard.getPaper1(),
				admitCard.getPaper1_date());
		addPaperIfNotNull(paperList, admitCard.getSub2_paper1_code(), admitCard.getExam_paper2(), admitCard.getPaper2(),
				admitCard.getPaper2_date());
		addPaperIfNotNull(paperList, admitCard.getSub3_paper1_code(), admitCard.getEam_paper3(), admitCard.getPaper3(),
				admitCard.getPaper3_date());
		addPaperIfNotNull(paperList, admitCard.getSub4_paper1_code(), admitCard.getExam_paper4(), admitCard.getPaper4(),
				admitCard.getPaper4_date());
		addPaperIfNotNull(paperList, admitCard.getSub5_paper1_code(), admitCard.getExam_paper5(), admitCard.getPaper5(),
				admitCard.getPaper5_date());
		addPaperIfNotNull(paperList, admitCard.getSub6_paper1_code(), admitCard.getExam_paper6(), admitCard.getPaper6(),
				admitCard.getPaper6_date());
		addPaperIfNotNull(paperList, admitCard.getSub7_paper1_code(), admitCard.getExam_paper7(), admitCard.getPaper7(),
				admitCard.getPaper7_date());
		addPaperIfNotNull(paperList, admitCard.getSub8_paper1_code(), admitCard.getExam_paper8(), admitCard.getPaper8(),
				admitCard.getPaper8_date());

		bodyMap.put("papers", paperList);
		return bodyMap;
	}
	
	public String getSemesterName(String sem) {
	    switch (sem) {
	        case "1":
	            return "First Semester";
	        case "2":
	            return "Second Semester";
	        case "3":
	            return "Third Semester";
	        case "4":
	            return "Fourth Semester";
	        case "5":
	            return "Fifth Semester";
	        case "6":
	            return "Sixth Semester";
	        case "7":
	            return "Seventh Semester";
	        case "8":
	            return "Eighth Semester";
	        default:
	            return "Invalid Semester";
	    }
	}

	@Override
	public Map<String, Object> getStuResultById(String rollno) {
	    List<StuResultView> stuResultById = noticeRepo.getStuResultById(rollno);
	    Map<String, Object> resultList = new HashMap<>();
	    
	    if (stuResultById.isEmpty()) {
	        throw new ConfigDataNotFound("No result data found for the student rollno: " + rollno);
	    }
	    
	    Map<String, Object> header = new HashMap<>();
	   
	    header.put("sName", stuResultById.get(0).getSname());
	    header.put("registrationNo", stuResultById.get(0).getRegistration_no());
	    header.put("rollNo", stuResultById.get(0).getRollno());
	    header.put("collegeName", stuResultById.get(0).getCollege_name());
	    header.put("session", stuResultById.get(0).getSassion());
	    header.put("stream", stuResultById.get(0).getStream());
	    header.put("collegeCode", stuResultById.get(0).getCollege_code());

	    resultList.put("header", header);

	    List<Map<String, Object>> bodyList = new ArrayList<>(); 

	    for (StuResultView resultView : stuResultById) {
	        Map<String, Object> subHeader = new HashMap<>();
	        Map<String, Object> subBody = new HashMap<>(); 

	        subHeader.put("course", resultView.getCourse());
	        subHeader.put("semYrCode", resultView.getSemyr_code());
	        subHeader.put("heldYear", resultView.getHeldyear());
	        subHeader.put("heldMonth", resultView.getHeldmonth());
	        subBody.put("subHeader", subHeader);
	        subBody.put("subBody", resultView); 
	        bodyList.add(subBody);
	    }

	    resultList.put("body", bodyList); 
	    return resultList;
	}
	

	@Override
	public Object getStuResultByIdAndSem(String rollno, String sem) {
		StuResultView stuResultByIdAndSem = noticeRepo.getStuResultByIdAndSem(rollno, sem);
		 Map<String, Object> header = new HashMap<>();
		   
		    header.put("sName", stuResultByIdAndSem.getSname());
		    header.put("registrationNo", stuResultByIdAndSem.getRegistration_no());
		    header.put("rollNo", stuResultByIdAndSem.getRollno());
		    header.put("collegeName", stuResultByIdAndSem.getCollege_name());
		    header.put("collegeCode", stuResultByIdAndSem.getCollege_code());
		    header.put("session", stuResultByIdAndSem.getSassion());
		    header.put("semYrCode", stuResultByIdAndSem.getSemyr_code());
		    header.put("heldMonth", stuResultByIdAndSem.getHeldmonth());
		    header.put("heldYear", stuResultByIdAndSem.getHeldyear());
		    header.put("slno", stuResultByIdAndSem.getSlno());
		    header.put("course", stuResultByIdAndSem.getCourse());
		    
		    Map<String, Object> subbody = new HashMap<>();
		    subbody.put("grandTotal", stuResultByIdAndSem.getTotal_full_marks());
		    subbody.put("sgpa", stuResultByIdAndSem.getSgpa());
		    subbody.put("percentage", stuResultByIdAndSem.getCradit_earned());
		    subbody.put("semesterResult", stuResultByIdAndSem.getSemester_result());
		    subbody.put("grade", null);
		    
		    List<Map<String, String>> paperList = new ArrayList<>();
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper1_a(), stuResultByIdAndSem.getPaper1_d(), stuResultByIdAndSem.getPaper1_c(), stuResultByIdAndSem.getPaper1_pr(),stuResultByIdAndSem.getPaper1_th(),stuResultByIdAndSem.getPaper1_tot(),stuResultByIdAndSem.getPaper1_grdp(),stuResultByIdAndSem.getPaper1_grd(), 1);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper2_a(), stuResultByIdAndSem.getPaper2_d(), stuResultByIdAndSem.getPaper2_c(), stuResultByIdAndSem.getPaper2_pr(),stuResultByIdAndSem.getPaper2_th(),stuResultByIdAndSem.getPaper2_tot(),stuResultByIdAndSem.getPaper2_grdp(),stuResultByIdAndSem.getPaper2_grd(), 2);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper3_a(), stuResultByIdAndSem.getPaper3_d(), stuResultByIdAndSem.getPaper3_c(), stuResultByIdAndSem.getPaper3_pr(),stuResultByIdAndSem.getPaper3_th(),stuResultByIdAndSem.getPaper3_tot(),stuResultByIdAndSem.getPaper3_grdp(),stuResultByIdAndSem.getPaper3_grd(), 3);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper4_a(), stuResultByIdAndSem.getPaper4_d(), stuResultByIdAndSem.getPaper4_c(), stuResultByIdAndSem.getPaper4_pr(),stuResultByIdAndSem.getPaper4_th(),stuResultByIdAndSem.getPaper4_tot(),stuResultByIdAndSem.getPaper4_grdp(),stuResultByIdAndSem.getPaper4_grd(), 4);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper5_a(), stuResultByIdAndSem.getPaper5_d(), stuResultByIdAndSem.getPaper5_c(), stuResultByIdAndSem.getPaper5_pr(),stuResultByIdAndSem.getPaper5_th(),stuResultByIdAndSem.getPaper5_tot(),stuResultByIdAndSem.getPaper5_grdp(),stuResultByIdAndSem.getPaper5_grd(), 5);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper6_a(), stuResultByIdAndSem.getPaper6_d(), stuResultByIdAndSem.getPaper6_c(), stuResultByIdAndSem.getPaper6_pr(),stuResultByIdAndSem.getPaper6_th(),stuResultByIdAndSem.getPaper6_tot(),stuResultByIdAndSem.getPaper6_grdp(),stuResultByIdAndSem.getPaper6_grd(), 6);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper7_a(), stuResultByIdAndSem.getPaper7_d(), stuResultByIdAndSem.getPaper7_c(), stuResultByIdAndSem.getPaper7_pr(),stuResultByIdAndSem.getPaper7_th(),stuResultByIdAndSem.getPaper7_tot(),stuResultByIdAndSem.getPaper7_grdp(),stuResultByIdAndSem.getPaper7_grd(), 7);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper8_a(), stuResultByIdAndSem.getPaper8_d(), stuResultByIdAndSem.getPaper8_c(), stuResultByIdAndSem.getPaper8_pr(),stuResultByIdAndSem.getPaper8_th(),stuResultByIdAndSem.getPaper8_tot(),stuResultByIdAndSem.getPaper8_grdp(),stuResultByIdAndSem.getPaper8_grd(), 8);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper9_a(), stuResultByIdAndSem.getPaper9_d(), stuResultByIdAndSem.getPaper9_c(), stuResultByIdAndSem.getPaper9_pr(),stuResultByIdAndSem.getPaper9_th(),stuResultByIdAndSem.getPaper9_tot(),stuResultByIdAndSem.getPaper9_grdp(),stuResultByIdAndSem.getPaper9_grd(), 9);
		    addPaperResultIfNotNull(paperList, stuResultByIdAndSem.getPaper10_a(), stuResultByIdAndSem.getPaper10_d(), stuResultByIdAndSem.getPaper10_c(), stuResultByIdAndSem.getPaper10_pr(),stuResultByIdAndSem.getPaper10_th(),stuResultByIdAndSem.getPaper10_tot(),stuResultByIdAndSem.getPaper10_grdp(),stuResultByIdAndSem.getPaper10_grd(), 10);
		    
		    Map<String, Object> bodyMap = new HashMap<>();
		    bodyMap.put("header", header);
		    bodyMap.put("subBody", subbody);
		    bodyMap.put("totalDetails", paperList);
		return bodyMap;
	}

	private void addPaperResultIfNotNull(List<Map<String, String>> paperList, String paperName, String paperCode, String paperSubCode, String paperPr,String paperTh,String paperTot,String paperGrdp,String paperGrd,int index) {
	    if (paperName != null) {
	        Map<String, String> paperMap = new HashMap<>();
	        paperMap.put("paperName", paperName);
	        //paperMap.put("paperCode", paperCode);
	        paperMap.put("paperCode", paperSubCode);
	        paperMap.put("paperPr", paperPr);
	        paperMap.put("paperTh", paperTh);
	        paperMap.put("paperTot", paperTot);
	        paperMap.put("gradePoint", paperGrdp);
	        paperMap.put("grade", paperGrd);
	        paperMap.put("credit", null);
	        paperMap.put("paperMaxPr", null);
	        paperMap.put("paperMaxTh", null);
	        paperMap.put("paperMaxTot", null);
	        paperList.add(paperMap);
	    }
	}
	
	
	@Override
	public List<StuExamView> getStuExamById(String rollno) {
		return noticeRepo.getStuExamById(rollno);
	}

}
