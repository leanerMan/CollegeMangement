package com.app.college.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StuResultView {

	 	@JsonProperty("slno")
	    String getSlno();

//	    @JsonProperty("id")
//	    String getId();

	    @JsonProperty("examid")
	    String getExamid();

//	    @JsonProperty("formno")
//	    String getFormno();
//
//	    @JsonProperty("admitid")
//	    String getAdmitid();
//
//	    @JsonProperty("examformStatus")
//	    String getExamform_status();
//
//	    @JsonProperty("resultStatus")
//	    String getResult_status();
//
//	    @JsonProperty("paymentStatus")
//	    String getPayment_status();

	    @JsonProperty("semyrCode")
	    String getSemyr_code();
	    
	    String getHeldyear();
	    
	    String getHeldmonth();

	    @JsonProperty("sname")
	    String getSname();

//	    @JsonProperty("nameInHindi")
//	    String getName_in_hindi();

//	    @JsonProperty("fname")
//	    String getFname();
//
//	    @JsonProperty("mname")
//	    String getMname();
//
//	    @JsonProperty("dob")
//	    String getDob();
//
//	    @JsonProperty("dobt")
//	    String getDobt();
//
//	    @JsonProperty("disability")
//	    String getDisability();
//
//	    @JsonProperty("category")
//	    String getCategory();

//	    @JsonProperty("clrollno")
//	    String getClrollno();
//
//	    @JsonProperty("regflag")
//	    String getRegflag();

	    @JsonProperty("registrationNo")
	    String getRegistration_no();

	    @JsonProperty("regyear")
	    String getRegyear();

//	    @JsonProperty("uploadedImage")
//	    String getUploaded_image();
//
//	    @JsonProperty("uploadedSignature")
//	    String getUploaded_signature();

	    @JsonProperty("collegeCode")
	    String getCollege_code();

	    @JsonProperty("collegeName")
	    String getCollege_name();

//	    @JsonProperty("centreCode")
//	    String getCentre_code();
//
//	    @JsonProperty("centreName")
//	    String getCentre_name();

//	    @JsonProperty("mobile")
//	    String getMobile();
//
//	    @JsonProperty("email")
//	    String getEmail();
//
//	    @JsonProperty("address")
//	    String getAddress();
//
//	    @JsonProperty("bpl")
//	    String getBpl();

	    @JsonProperty("rollno")
	    String getRollno();

//	    @JsonProperty("ansidrno")
//	    String getAnsidrno();

	    @JsonProperty("sassion")
	    String getSassion();

	    @JsonProperty("stream")
	    String getStream();

	    @JsonProperty("course")
	    String getCourse();

	    @JsonProperty("branch")
	    String getBranch();

//	    @JsonProperty("courseCode")
//	    String getCourse_code();

	    @JsonProperty("examType")
	    String getExamType();

//	    @JsonProperty("gender")
//	    String getGender();
//
//	    @JsonProperty("nationality")
//	    String getNationality();

//	    @JsonProperty("branchName")
//	    String getBranch_name();
//
//	    @JsonProperty("subunicode")
//	    String getSubunicode();

	    @JsonProperty("subcode")
	    String getSubcode();

	    @JsonProperty("subject")
	    String getSubject();

	    @JsonProperty("paper1Name")
	    String getPaper1_a();

	    @JsonProperty("paper1Code")
	    String getPaper1_d();

	    @JsonProperty("paper1SubCode")
	    String getPaper1_c();

//	    @JsonProperty("paper1N")
//	    String getPaper1_n();
//
//	    @JsonProperty("paper1Int")
//	    String getPaper1_int();

	    @JsonProperty("paper1Pr")
	    String getPaper1_pr();

	    @JsonProperty("paper1Th")
	    String getPaper1_th();

	    @JsonProperty("paper1Tot")
	    String getPaper1_tot();

	    @JsonProperty("paper1Grdp")
	    String getPaper1_grdp();

	    @JsonProperty("paper1Grd")
	    String getPaper1_grd();

//	    @JsonProperty("paper1Result")
//	    String getPaper1_result();
//
//	    @JsonProperty("paper1ObtainedCradit")
//	    String getPaper1_obtained_cradit();
	    
	    @JsonProperty("paper2Name")
	    String getPaper2_a();
	    
	    @JsonProperty("paper2Code")
	    String getPaper2_d();
	    
	    @JsonProperty("paper2SubCode")
	    String getPaper2_c();
	    
//	    @JsonProperty("paper2N")
//	    String getPaper2_n();
//
//	    @JsonProperty("paper2Int")
//	    String getPaper2_int();
	    
	    @JsonProperty("paper2Pr")
	    String getPaper2_pr();
	    
	    @JsonProperty("paper2Th")
	    String getPaper2_th();
	    
	    @JsonProperty("paper2Tot")
	    String getPaper2_tot();
	    
	    @JsonProperty("paper2Grdp")
	    String getPaper2_grdp();
	    
	    @JsonProperty("paper2Grd")
	    String getPaper2_grd();
	    
//	    @JsonProperty("paper2Result")
//	    String getPaper2_result();
//
//	    @JsonProperty("paper2ObtainedCradit")
//	    String getPaper2_obtained_cradit();
	    
	    @JsonProperty("paper3Name")
	    String getPaper3_a();
	    
	    @JsonProperty("paper3Code")
	    String getPaper3_d();
	    
	    @JsonProperty("paper3SubCode")
	    String getPaper3_c();
	    
//	    @JsonProperty("paper3N")
//	    String getPaper3_n();
//
//	    @JsonProperty("paper3Int")
//	    String getPaper3_int();
	    
	    @JsonProperty("paper3Pr")
	    String getPaper3_pr();
	    
	    @JsonProperty("paper3Th")
	    String getPaper3_th();
	    
	    @JsonProperty("paper3Tot")
	    String getPaper3_tot();
	    
	    @JsonProperty("paper3Grdp")
	    String getPaper3_grdp();
	    
	    @JsonProperty("paper3Grd")
	    String getPaper3_grd();
	    
//	    @JsonProperty("paper3Result")
//	    String getPaper3_result();
//
//	    @JsonProperty("paper3ObtainedCradit")
//	    String getPaper3_obtained_cradit();
	    
	    @JsonProperty("paper4Name")
	    String getPaper4_a();
	    
	    @JsonProperty("paper4Code")
	    String getPaper4_d();
	    
	    @JsonProperty("paper4SubCode")
	    String getPaper4_c();
	    
//	    @JsonProperty("paper4N")
//	    String getPaper4_n();
//
//	    @JsonProperty("paper4Int")
//	    String getPaper4_int();
	    
	    @JsonProperty("paper4Pr")
	    String getPaper4_pr();
	    
	    @JsonProperty("paper4Th")
	    String getPaper4_th();
	    
	    @JsonProperty("paper4Tot")
	    String getPaper4_tot();
	    
	    @JsonProperty("paper4Grdp")
	    String getPaper4_grdp();
	    
	    @JsonProperty("paper4Grd")
	    String getPaper4_grd();
	    
//	    @JsonProperty("paper4Result")
//	    String getPaper4_result();
//
//	    @JsonProperty("paper4ObtainedCradit")
//	    String getPaper4_obtained_cradit();

	    @JsonProperty("paper5Name")
	    String getPaper5_a();
	    
	    @JsonProperty("paper5Code")
	    String getPaper5_d();
	    
	    @JsonProperty("paper5SubCode")
	    String getPaper5_c();
	    
//	    @JsonProperty("paper5N")
//	    String getPaper5_n();
//
//	    @JsonProperty("paper5Int")
//	    String getPaper5_int();
	    
	    @JsonProperty("paper5Pr")
	    String getPaper5_pr();
	    
	    @JsonProperty("paper5Th")
	    String getPaper5_th();
	    
	    @JsonProperty("paper5Tot")
	    String getPaper5_tot();
	    
	    @JsonProperty("paper5Grdp")
	    String getPaper5_grdp();
	    
	    @JsonProperty("paper5Grd")
	    String getPaper5_grd();
	    
//	    @JsonProperty("paper5Result")
//	    String getPaper5_result();
//
//	    @JsonProperty("paper5ObtainedCradit")
//	    String getPaper5_obtained_cradit();

	    @JsonProperty("paper6Name")
	    String getPaper6_a();
	    
	    @JsonProperty("paper6Code")
	    String getPaper6_d();
	    
	    @JsonProperty("paper6SubCode")
	    String getPaper6_c();
	    
//	    @JsonProperty("paper6N")
//	    String getPaper6_n();
//
//	    @JsonProperty("paper6Int")
//	    String getPaper6_int();
	    
	    @JsonProperty("paper6Pr")
	    String getPaper6_pr();
	    
	    @JsonProperty("paper6Th")
	    String getPaper6_th();
	    
	    @JsonProperty("paper6Tot")
	    String getPaper6_tot();
	    
	    @JsonProperty("paper6Grdp")
	    String getPaper6_grdp();
	    
	    @JsonProperty("paper6Grd")
	    String getPaper6_grd();
	    
//	    @JsonProperty("paper6Result")
//	    String getPaper6_result();
//
//	    @JsonProperty("paper6ObtainedCradit")
//	    String getPaper6_obtained_cradit();

	    @JsonProperty("paper7Name")
	    String getPaper7_a();
	    
	    @JsonProperty("paper7Code")
	    String getPaper7_d();
	    
	    @JsonProperty("paper7SubCode")
	    String getPaper7_c();
	    
//	    @JsonProperty("paper7N")
//	    String getPaper7_n();
//
//	    @JsonProperty("paper7Int")
//	    String getPaper7_int();
	    
	    @JsonProperty("paper7Pr")
	    String getPaper7_pr();
	    
	    @JsonProperty("paper7Th")
	    String getPaper7_th();
	    
	    @JsonProperty("paper7Tot")
	    String getPaper7_tot();
	    
	    @JsonProperty("paper7Grdp")
	    String getPaper7_grdp();
	    
	    @JsonProperty("paper7Grd")
	    String getPaper7_grd();
	    
//	    @JsonProperty("paper7Result")
//	    String getPaper7_result();
//
//	    @JsonProperty("paper7ObtainedCradit")
//	    String getPaper7_obtained_cradit();

	    @JsonProperty("paper8Name")
	    String getPaper8_a();
	    
	    @JsonProperty("paper8Code")
	    String getPaper8_d();
	    
	    @JsonProperty("paper8SubCode")
	    String getPaper8_c();
	    
//	    @JsonProperty("paper8N")
//	    String getPaper8_n();
//
//	    @JsonProperty("paper8Int")
//	    String getPaper8_int();
	    
	    @JsonProperty("paper8Pr")
	    String getPaper8_pr();
	    
	    @JsonProperty("paper8Th")
	    String getPaper8_th();
	    
	    @JsonProperty("paper8Tot")
	    String getPaper8_tot();
	    
	    @JsonProperty("paper8Grdp")
	    String getPaper8_grdp();
	    
	    @JsonProperty("paper8Grd")
	    String getPaper8_grd();
	    
//	    @JsonProperty("paper8Result")
//	    String getPaper8_result();
//
//	    @JsonProperty("paper8ObtainedCradit")
//	    String getPaper8_obtained_cradit();

	    @JsonProperty("paper9Name")
	    String getPaper9_a();
	    
	    @JsonProperty("paper9Code")
	    String getPaper9_d();
	    
	    @JsonProperty("paper9SubCode")
	    String getPaper9_c();
	    
//	    @JsonProperty("paper9N")
//	    String getPaper9_n();
//
//	    @JsonProperty("paper9Int")
//	    String getPaper9_int();
	    
	    @JsonProperty("paper9Pr")
	    String getPaper9_pr();
	    
	    @JsonProperty("paper9Th")
	    String getPaper9_th();
	    
	    @JsonProperty("paper9Tot")
	    String getPaper9_tot();
	    
	    @JsonProperty("paper9Grdp")
	    String getPaper9_grdp();
	    
	    @JsonProperty("paper9Grd")
	    String getPaper9_grd();
	    
//	    @JsonProperty("paper9Result")
//	    String getPaper9_result();
//
//	    @JsonProperty("paper9ObtainedCradit")
//	    String getPaper9_obtained_cradit();

	    @JsonProperty("paper10Name")
	    String getPaper10_a();
	    
	    @JsonProperty("paper10Code")
	    String getPaper10_d();
	    
	    @JsonProperty("paper10SubCode")
	    String getPaper10_c();
	    
//	    @JsonProperty("paper10N")
//	    String getPaper10_n();
//
//	    @JsonProperty("paper10Int")
//	    String getPaper10_int();
	    
	    @JsonProperty("paper10Pr")
	    String getPaper10_pr();
	    
	    @JsonProperty("paper10Th")
	    String getPaper10_th();
	    
	    @JsonProperty("paper10Tot")
	    String getPaper10_tot();
	    
	    @JsonProperty("paper10Grdp")
	    String getPaper10_grdp();
	    
	    @JsonProperty("paper10Grd")
	    String getPaper10_grd();
	    
//	    @JsonProperty("paper10Result")
//	    String getPaper10_result();
//
//	    @JsonProperty("paper10ObtainedCradit")
//	    String getPaper10_obtained_cradit();

//	    String getHontotal();
//	    String getGetot();
//	    String getGe1tot();
//	    String getSectot();
	    String getGrandtotal();
	    String getSgpa();
	    @JsonProperty("craditEarned")
	    String getCradit_earned();
	    @JsonProperty("finalResult")
	    String getFinal_result();
	    @JsonProperty("semesterResult")
	    String getSemester_result();
//	    String getCradit();
	    String getRemarks();
	    @JsonProperty("paper1FullMarks")
	    String getPaper1_full_marks();
	    @JsonProperty("paper2FullMarks")
	    String getPaper2_full_marks();
	    @JsonProperty("paper3FullMarks")
	    String getPaper3_full_marks();
	    @JsonProperty("paper4FullMarks")
	    String getPaper4_full_marks();
	    @JsonProperty("paper5FullMarks")
	    String getPaper5_full_marks();
	    @JsonProperty("paper6FullMarks")
	    String getPaper6_full_marks();
	    @JsonProperty("paper7FullMarks")
	    String getPaper7_full_marks();
	    @JsonProperty("paper8FullMarks")
	    String getPaper8_full_marks();
	    @JsonProperty("paper9FullMarks")
	    String getPaper9_full_marks();
	    @JsonProperty("paper10FullMarks")
	    String getPaper10_full_marks();
//	    String getPaper11_full_marks();
//	    String getPaper12_full_marks();
	    @JsonProperty("totalFullMarks")
	    String getTotal_full_marks();
//	    String getPercentage();
//	    @JsonProperty("finalGrade")
//	    String getFinal_grade();
//	    @JsonProperty("fGrace")
//	    String getF_grace();
//	    @JsonProperty("fGracemks")
//	    String getF_gracemks();
//	    @JsonProperty("fGraceremark")
//	    String getF_graceremark();
//	    @JsonProperty("fRemark")
//	    String getF_remark();
}
