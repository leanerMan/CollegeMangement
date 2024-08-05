package com.app.college.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StuAdmidCardUgPgView {

	@JsonProperty("examId") String getExam_id();
//    @JsonProperty("formNo") String  getForm_no();
    @JsonProperty("studentId") String  getStudent_id();//
    @JsonProperty("userId") String  getUser_id();//
    @JsonProperty("EId") String  getE_id();//
    @JsonProperty("rollNo") String  getRoll_no();//
    @JsonProperty("regNo") String  getRegno();//
    @JsonProperty("regYear") String  getRegyear();//
    @JsonProperty("ansIdRno") String  getAnsidrno();
    @JsonProperty("examYear") String  getExamyear();//
    @JsonProperty("heldYear") String  getHeldyear();//
    @JsonProperty("heldMonth") String  getHeldmonth();//
//    @JsonProperty("admitSno") String  getAdmit_sno();
    @JsonProperty("examStartDate") String  getExamstart_date();//
//    @JsonProperty("admitStatus") String  getAdmit_status();
    @JsonProperty("sName") String  getSname();//
//    @JsonProperty("stuNameHindi") String  getStu_name_hindi();
    @JsonProperty("dob") String  getDob();//
    @JsonProperty("fName") String  getFname();//
//    @JsonProperty("fNameHindi") String  getFname_hindi();
//    @JsonProperty("mName") String  getMname();
//    @JsonProperty("mNameHindi") String  getMname_hindi();
//    @JsonProperty("gender") String  getGender();
//    @JsonProperty("category") String  getCategory();
    @JsonProperty("profileImage") String  getProfile_image();//
    @JsonProperty("signatureImage") String  getSignature_image();//
    @JsonProperty("studentType") String  getStudent_type();//
//    @JsonProperty("examformStatus") String  getExamform_status();
//    @JsonProperty("mobileNo") String  getMobileno();
//    @JsonProperty("email") String  getEmail();
//    @JsonProperty("aadharNo") String  getAadhar_no();
//    @JsonProperty("bloodGroup") String  getBlood_group();
//    @JsonProperty("pAddress") String  getP_address();
//    @JsonProperty("pDistrict") String  getP_district();
//    @JsonProperty("pPin") String  getP_pin();
//    @JsonProperty("pState") String  getP_state();
//    @JsonProperty("admissionDate") String  getAdmission_date();
//    @JsonProperty("paymentAmount") String  getPayment_amount();
//    @JsonProperty("paymentDate") String  getPayment_date();
//    @JsonProperty("paymentStatus") String  getPayment_status();
//    @JsonProperty("classRollNo") String  getClass_rollno();
    @JsonProperty("collegeCode") String  getCollege_code();//
    @JsonProperty("collegeName") String  getCollege_name();//
    @JsonProperty("centerCode") String  getCenter_code();//
    @JsonProperty("centerName") String  getCenter_name();//
    @JsonProperty("ses") String  getSes();//
//    @JsonProperty("batch") String  getBatch();
    @JsonProperty("examType") String  getExam_type();
    @JsonProperty("sem") String  getSem();
    @JsonProperty("course") String  getCourse();//
    @JsonProperty("courseType") String  getCourse_type();//
//    @JsonProperty("stream") String  getStream();
    @JsonProperty("faculty") String  getFaculty();//
//    @JsonProperty("subject") String  getSubject();
//    @JsonProperty("subjectCode") String  getSubject_code();
    @JsonProperty("paper1") String  getPaper1();//
    @JsonProperty("sub1Paper1Code") String  getSub1_paper1_code();//
//    @JsonProperty("sub1Paper2Code") String  getSub1_paper2_code();//
//    @JsonProperty("sub1Paper3Code") String  getSub1_paper3_code();//
    @JsonProperty("codePaper1") String  getExam_paper1();//
//    @JsonProperty("paper1Ncode") String  getPaper1_ncode();//
    @JsonProperty("paper1Date") String  getPaper1_date();//
//    @JsonProperty("paper1Sit") String  getPaper1_sit();
//    @JsonProperty("paper1Int") String  getPaper1_int();
//    @JsonProperty("paper1Th") String  getPaper1_th();
//    @JsonProperty("paper1Pr") String  getPaper1_pr();
//    @JsonProperty("paper1Tot") String  getPaper1_tot();
//    @JsonProperty("paper1Grd") String  getPaper1_grd();
//    @JsonProperty("paper1Pnt") String  getPaper1_pnt();
//    @JsonProperty("paper1Crt") String  getPaper1_crt();
//    @JsonProperty("paper1CrtObt") String  getPaper1_crt_obt();
    @JsonProperty("paper2") String  getPaper2();//
    @JsonProperty("sub2Paper1Code") String  getSub2_paper1_code();//
//    @JsonProperty("sub2Paper2Code") String  getSub2_paper2_code();//
//    @JsonProperty("sub2Paper3Code") String  getSub2_paper3_code();//
    @JsonProperty("codePaper2") String  getExam_paper2();//
//    @JsonProperty("paper2Ncode") String  getPaper2_ncode();//
    @JsonProperty("paper2Date") String  getPaper2_date();//
//    @JsonProperty("paper2Sit") String  getPaper2_sit();
//    @JsonProperty("paper2Int") String  getPaper2_int();
//    @JsonProperty("paper2Th") String  getPaper2_th();
//    @JsonProperty("paper2Pr") String  getPaper2_pr();
//    @JsonProperty("paper2Tot") String  getPaper2_tot();
//    @JsonProperty("paper2Grd") String  getPaper2_grd();
//    @JsonProperty("paper2Pnt") String  getPaper2_pnt();
//    @JsonProperty("paper2Crt") String  getPaper2_crt();
//    @JsonProperty("paper2CrtObt") String  getPaper2_crt_obt();
    @JsonProperty("paper3") String  getPaper3();
    @JsonProperty("sub3Paper1Code") String  getSub3_paper1_code();
    @JsonProperty("codePaper3") String  getEam_paper3();
    @JsonProperty("paper3Date") String  getPaper3_date();
//    @JsonProperty("paper3Sit") String  getPaper3_sit();
//    @JsonProperty("paper3Int") String  getPaper3_int();
//    @JsonProperty("paper3Th") String  getPaper3_th();
//    @JsonProperty("paper3Pr") String  getPaper3_pr();
//    @JsonProperty("paper3Tot") String  getPaper3_tot();
//    @JsonProperty("paper3Grd") String  getPaper3_grd();
//    @JsonProperty("paper3Pnt") String  getPaper3_pnt();
//    @JsonProperty("paper3Crt") String  getPaper3_crt();
//    @JsonProperty("paper3CrtObt") String  getPaper3_crt_obt();
    @JsonProperty("paper4") String  getPaper4();
    @JsonProperty("sub4Paper1Code") String  getSub4_paper1_code();
    @JsonProperty("codePaper4") String  getExam_paper4();
    @JsonProperty("paper4Date") String  getPaper4_date();
//    @JsonProperty("paper4Sit") String  getPaper4_sit();
//    @JsonProperty("paper4Int") String  getPaper4_int();
//    @JsonProperty("paper4Th") String  getPaper4_th();
//    @JsonProperty("paper4Pr") String  getPaper4_pr();
//    @JsonProperty("paper4Tot") String  getPaper4_tot();
//    @JsonProperty("paper4Grd") String  getPaper4_grd();
//    @JsonProperty("paper4Pnt") String  getPaper4_pnt();
//    @JsonProperty("paper4Crt") String  getPaper4_crt();
//    @JsonProperty("paper4CrtObt") String  getPaper4_crt_obt();
    @JsonProperty("paper5") String  getPaper5();
    @JsonProperty("sub5Paper1Code") String  getSub5_paper1_code();
    @JsonProperty("codePaper5") String  getExam_paper5();
    @JsonProperty("paper5Date") String  getPaper5_date();
//    @JsonProperty("paper5Sit") String  getPaper5_sit();
//    @JsonProperty("paper5Int") String  getPaper5_int();
//    @JsonProperty("paper5Th") String  getPaper5_th();
//    @JsonProperty("paper5Pr") String  getPaper5_pr();
//    @JsonProperty("paper5Tot") String  getPaper5_tot();
//    @JsonProperty("paper5Grd") String  getPaper5_grd();
//    @JsonProperty("paper5Pnt") String  getPaper5_pnt();
//    @JsonProperty("paper5Crt") String  getPaper5_crt();
//    @JsonProperty("paper5CrtObt") String  getPaper5_crt_obt();
    @JsonProperty("paper6") String  getPaper6();
    @JsonProperty("sub6Paper1Code") String  getSub6_paper1_code();
    @JsonProperty("codePaper6") String  getExam_paper6();
    @JsonProperty("paper6Date") String  getPaper6_date();
//    @JsonProperty("paper6Sit") String  getPaper6_sit();
//    @JsonProperty("paper6Int") String  getPaper6_int();
//    @JsonProperty("paper6Th") String  getPaper6_th();
//    @JsonProperty("paper6Pr") String  getPaper6_pr();
//    @JsonProperty("paper6Tot") String  getPaper6_tot();
//    @JsonProperty("paper6Grd") String  getPaper6_grd();
//    @JsonProperty("paper6Pnt") String  getPaper6_pnt();
//    @JsonProperty("paper6Crt") String  getPaper6_crt();
//    @JsonProperty("paper6CrtObt") String  getPaper6_crt_obt();
    @JsonProperty("paper7") String  getPaper7();
    @JsonProperty("sub7Paper1Code") String  getSub7_paper1_code();
    @JsonProperty("codePaper7") String  getExam_paper7();
    @JsonProperty("paper7Date") String  getPaper7_date();
//    @JsonProperty("paper7Sit") String  getPaper7_sit();
//    @JsonProperty("paper7Int") String  getPaper7_int();
//    @JsonProperty("paper7Th") String  getPaper7_th();
//    @JsonProperty("paper7Pr") String  getPaper7_pr();
//    @JsonProperty("paper7Tot") String  getPaper7_tot();
//    @JsonProperty("paper7Grd") String  getPaper7_grd();
//    @JsonProperty("paper7Pnt") String  getPaper7_pnt();
//    @JsonProperty("paper7Crt") String  getPaper7_crt();
//    @JsonProperty("paper7CrtObt") String  getPaper7_crt_obt();
    @JsonProperty("paper8") String  getPaper8();
    @JsonProperty("sub8Paper1Code") String  getSub8_paper1_code();
    @JsonProperty("codePaper8") String  getExam_paper8();
    @JsonProperty("paper8Date") String  getPaper8_date();
//    @JsonProperty("paper8Sit") String  getPaper8_sit();
//    @JsonProperty("paper8Int") String  getPaper8_int();
//    @JsonProperty("paper8Th") String  getPaper8_th();
//    @JsonProperty("paper8Pr") String  getPaper8_pr();
//    @JsonProperty("paper8Tot") String  getPaper8_tot();
//    @JsonProperty("paper8Grd") String  getPaper8_grd();
//    @JsonProperty("paper8Pnt") String  getPaper8_pnt();
//    @JsonProperty("paper8Crt") String  getPaper8_crt();
//    @JsonProperty("paper8CrtObt") String  getPaper8_crt_obt();
//    @JsonProperty("paper9") String  getPaper9();
//    @JsonProperty("paper9Code") String  getPaper9_code();
//    @JsonProperty("paper9Ncode") String  getPaper9_ncode();
//    @JsonProperty("paper9Date") String  getPaper9_date();
//    @JsonProperty("paper9Sit") String  getPaper9_sit();
//    @JsonProperty("paper9Int") String  getPaper9_int();
//    @JsonProperty("paper9Th") String  getPaper9_th();
//    @JsonProperty("paper9Pr") String  getPaper9_pr();
//    @JsonProperty("paper9Tot") String  getPaper9_tot();
//    @JsonProperty("paper9Grd") String  getPaper9_grd();
//    @JsonProperty("paper9Pnt") String  getPaper9_pnt();
//    @JsonProperty("paper9Crt") String  getPaper9_crt();
//    @JsonProperty("paper9CrtObt") String  getPaper9_crt_obt();
//    @JsonProperty("paper10") String  getPaper10();
//    @JsonProperty("paper10Code") String  getPaper10_code();
//    @JsonProperty("paper10Ncode") String  getPaper10_ncode();
//    @JsonProperty("paper10Date") String  getPaper10_date();
//    @JsonProperty("paper10Sit") String  getPaper10_sit();
//    @JsonProperty("paper10Int") String  getPaper10_int();
//    @JsonProperty("paper10Th") String  getPaper10_th();
//    @JsonProperty("paper10Pr") String  getPaper10_pr();
//    @JsonProperty("paper10Tot") String  getPaper10_tot();
//    @JsonProperty("paper10Grd") String  getPaper10_grd();
//    @JsonProperty("paper10Pnt") String  getPaper10_pnt();
//    @JsonProperty("paper10Crt") String  getPaper10_crt();
//    @JsonProperty("paper10CrtObt") String  getPaper10_crt_obt();
//    @JsonProperty("paper11") String  getPaper11();
//    @JsonProperty("paper11Code") String  getPaper11_code();
//    @JsonProperty("paper11Ncode") String  getPaper11_ncode();
//    @JsonProperty("paper11Date") String  getPaper11_date();
//    @JsonProperty("paper11Sit") String  getPaper11_sit();
//    @JsonProperty("paper11Int") String  getPaper11_int();
//    @JsonProperty("paper11Th") String  getPaper11_th();
//    @JsonProperty("paper11Pr") String  getPaper11_pr();
//    @JsonProperty("paper11Tot") String  getPaper11_tot();
//    @JsonProperty("paper11Grd") String  getPaper11_grd();
//    @JsonProperty("paper11Pnt") String  getPaper11_pnt();
//    @JsonProperty("paper11Crt") String  getPaper11_crt();
//    @JsonProperty("paper11CrtObt") String  getPaper11_crt_obt();
//    @JsonProperty("paper12") String  getPaper12();
//    @JsonProperty("paper12Code") String  getPaper12_code();
//    @JsonProperty("paper12Ncode") String  getPaper12_ncode();
//    @JsonProperty("paper12Date") String  getPaper12_date();
//    @JsonProperty("paper12Sit") String  getPaper12_sit();
//    @JsonProperty("paper12Int") String  getPaper12_int();
//    @JsonProperty("paper12Th") String  getPaper12_th();
//    @JsonProperty("paper12Pr") String  getPaper12_pr();
//    @JsonProperty("paper12Tot") String  getPaper12_tot();
//    @JsonProperty("paper12Grd") String  getPaper12_grd();
//    @JsonProperty("paper12Pnt") String  getPaper12_pnt();
//    @JsonProperty("paper12Crt") String  getPaper12_crt();
//    @JsonProperty("paper12CrtObt") String  getPaper12_crt_obt();
//    @JsonProperty("honsTot") String  getHons_tot();
//    @JsonProperty("grandTot") String  getGrand_tot();
//    @JsonProperty("totalCredit") String  getTotal_credit();
//    @JsonProperty("totalCreditObt") String  getTotal_credit_obt();
//    @JsonProperty("sgpa") String  getSgpa();
//    @JsonProperty("cgpa") String  getCgpa();
//    @JsonProperty("reslult") String  getReslult();
//    @JsonProperty("remarks") String  getRemarks();
//    @JsonProperty("resultDate") String  getResult_date();
//    @JsonProperty("resultStatus") String  getResult_status();

}
