package com.app.college.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StuExamView {

	@JsonProperty("slno")
    String getSlno();

    @JsonProperty("id")
    String getId();

    @JsonProperty("examid")
    String getExamid();

    @JsonProperty("formno")
    String getFormno();

//    @JsonProperty("scanno")
//    String getScanno();

//    @JsonProperty("application_no")
//    String getApplication_no();

//    @JsonProperty("imscanno")
//    String getImscanno();

    @JsonProperty("sname")
    String getSname();

//    @JsonProperty("hname")
//    String getHname();

    @JsonProperty("fname")
    String getFname();

//    @JsonProperty("mname")
//    String getMname();

    @JsonProperty("dob")
    String getDob();

//    @JsonProperty("dobc")
//    String getDobc();
//
//    @JsonProperty("dobt")
//    String getDobt();

//    @JsonProperty("gender")
//    String getGender();

//    @JsonProperty("gender_code")
//    String getGender_code();

//    @JsonProperty("category")
//    String getCategory();
//
//    @JsonProperty("category_code")
//    String getCategory_code();

    @JsonProperty("yearOfExam")
    String getYear_of_exam();

//    @JsonProperty("heldyear")
//    String getHeldyear();
//
//    @JsonProperty("heldmonth")
//    String getHeldmonth();

//    @JsonProperty("address")
//    String getAddress();
//
//    @JsonProperty("bpl")
//    String getBpl();
//
//    @JsonProperty("ph")
//    String getPh();
//
//    @JsonProperty("ph_point")
//    String getPh_point();
//
//    @JsonProperty("email")
//    String getEmail();
//
//    @JsonProperty("marital_status")
//    String getMarital_status();
//
//    @JsonProperty("blood_group")
//    String getBlood_group();
//
//    @JsonProperty("religion")
//    String getReligion();
//
//    @JsonProperty("id_card")
//    String getId_card();
//
//    @JsonProperty("id_card_no")
//    String getId_card_no();
//
//    @JsonProperty("identification_mark")
//    String getIdentification_mark();
//
//    @JsonProperty("nationality")
//    String getNationality();
//
//    @JsonProperty("nri_country")
//    String getNri_country();
//
//    @JsonProperty("ncc")
//    String getNcc();
//
//    @JsonProperty("nss")
//    String getNss();
//
//    @JsonProperty("ews")
//    String getEws();
//
//    @JsonProperty("domicile")
//    String getDomicile();
//
//    @JsonProperty("student_id")
//    String getStudent_id();
//
//    @JsonProperty("userid")
//    String getUserid();

    @JsonProperty("studentPhoto")
    String getStudent_photo();

    @JsonProperty("studentSignature")
    String getStudent_signature();

//    @JsonProperty("mobile")
//    String getMobile();
//
//    @JsonProperty("phone")
//    String getPhone();
//
//    @JsonProperty("profile")
//    String getProfile();
//
//    @JsonProperty("status")
//    String getStatus();
//
//    @JsonProperty("admit_id")
//    String getAdmit_id();
//
//    @JsonProperty("uni_id")
//    String getUni_id();
//
//    @JsonProperty("id1")
//    String getId1();
//
//    @JsonProperty("prerollnof")
//    String getPrerollnof();

//    @JsonProperty("prerollno")
//    String getPrerollno();
//
//    @JsonProperty("stream")
//    String getStream();

//    @JsonProperty("course")
//    String getCourse();

    @JsonProperty("coursename")
    String getCoursename();

//    @JsonProperty("coursety")
//    String getCoursety();
//
//    @JsonProperty("courseid")
//    String getCourseid();
//
//    @JsonProperty("coid")
//    String getCoid();
//
//    @JsonProperty("mediam")
//    String getMediam();

    @JsonProperty("yearOfRegistration")
    String getYear_of_registration();

//    @JsonProperty("date_of_registration")
//    String getDate_of_registration();

    @JsonProperty("registrationNo")
    String getRegistration_no();

    @JsonProperty("sassion")
    String getSassion();

//    @JsonProperty("enrollment_no")
//    String getEnrollment_no();
//
//    @JsonProperty("ansidrno")
//    String getAnsidrno();

    @JsonProperty("rollno")
    String getRollno();

//    @JsonProperty("clrollno")
//    String getClrollno();
//
//    @JsonProperty("coll_code")
//    String getColl_code();
//
//    @JsonProperty("college_code")
//    String getCollege_code();

    @JsonProperty("collegeName")
    String getCollege_name();

//    @JsonProperty("university")
//    String getUniversity();

    @JsonProperty("centrecode")
    String getCentrecode();

    @JsonProperty("centrename")
    String getCentrename();

//    @JsonProperty("supcentrecode")
//    String getSupcentrecode();
//
//    @JsonProperty("supcentrename")
//    String getSupcentrename();

//    @JsonProperty("degree")
//    String getDegree();
//
//    @JsonProperty("degree_code")
//    String getDegree_code();
//
//    @JsonProperty("semyr")
//    String getSemyr();

    @JsonProperty("semyrCode")
    String getSemyr_code();

    @JsonProperty("studType")
    String getStud_type();

//    @JsonProperty("stud_code")
//    String getStud_code();
//
//    @JsonProperty("brcode")
//    String getBrcode();
//
//    @JsonProperty("branch")
//    String getBranch();
//
//    @JsonProperty("subcode")
//    String getSubcode();
//
//    @JsonProperty("subunivcode")
//    String getSubunivcode();
//
//    @JsonProperty("subject")
//    String getSubject();
//
//    @JsonProperty("sub1")
//    String getSub1();
//
//    @JsonProperty("sub1code")
//    String getSub1code();
//
//    @JsonProperty("sub2")
//    String getSub2();
//
//    @JsonProperty("sub2code")
//    String getSub2code();
//
//    @JsonProperty("sub3")
//    String getSub3();
//
//    @JsonProperty("sub3code")
//    String getSub3code();
//
//    @JsonProperty("sub4")
//    String getSub4();
//
//    @JsonProperty("sub4code")
//    String getSub4code();
//
//    @JsonProperty("sub5")
//    String getSub5();
//
//    @JsonProperty("sub5code")
//    String getSub5code();
//
//    @JsonProperty("sub6")
//    String getSub6();
//
//    @JsonProperty("sub6code")
//    String getSub6code();
//
//    @JsonProperty("sub7")
//    String getSub7();
//
//    @JsonProperty("sub7code")
//    String getSub7code();
//
//    @JsonProperty("sub8")
//    String getSub8();
//
//    @JsonProperty("sub8code")
//    String getSub8code();
//
//    @JsonProperty("sub9")
//    String getSub9();
//
//    @JsonProperty("sub9code")
//    String getSub9code();
//
//    @JsonProperty("sub10")
//    String getSub10();
//
//    @JsonProperty("sub10code")
//    String getSub10code();
//
//    @JsonProperty("sub11")
//    String getSub11();
//
//    @JsonProperty("sub11code")
//    String getSub11code();
//
//    @JsonProperty("sub12")
//    String getSub12();
//
//    @JsonProperty("sub12code")
//    String getSub12code();
//
//    @JsonProperty("sub1p1")
//    String getSub1p1();
//
//    @JsonProperty("sub1p1code")
//    String getSub1p1code();
//
//    @JsonProperty("sub1p2")
//    String getSub1p2();
//
//    @JsonProperty("sub1p2code")
//    String getSub1p2code();
//
//    @JsonProperty("sub2p1")
//    String getSub2p1();
//
//    @JsonProperty("sub2p1code")
//    String getSub2p1code();
//
//    @JsonProperty("sub2p2")
//    String getSub2p2();
//
//    @JsonProperty("sub2p2code")
//    String getSub2p2code();
//
//    @JsonProperty("sub3p1")
//    String getSub3p1();
//
//    @JsonProperty("sub3p1code")
//    String getSub3p1code();
//
//    @JsonProperty("sub3p2")
//    String getSub3p2();
//
//    @JsonProperty("sub3p2code")
//    String getSub3p2code();
//
//    @JsonProperty("sub4p1")
//    String getSub4p1();
//
//    @JsonProperty("sub4p1code")
//    String getSub4p1code();
//
//    @JsonProperty("sub4p2")
//    String getSub4p2();
//
//    @JsonProperty("sub4p2code")
//    String getSub4p2code();
//
//    @JsonProperty("sub5p1")
//    String getSub5p1();
//
//    @JsonProperty("sub5p1code")
//    String getSub5p1code();
//
//    @JsonProperty("sub5p2")
//    String getSub5p2();
//
//    @JsonProperty("sub5p2code")
//    String getSub5p2code();
//
//    @JsonProperty("sub6p1")
//    String getSub6p1();
//
//    @JsonProperty("sub6p1code")
//    String getSub6p1code();
//
//    @JsonProperty("sub6p2")
//    String getSub6p2();
//
//    @JsonProperty("sub6p2code")
//    String getSub6p2code();
//
//    @JsonProperty("sub7p1")
//    String getSub7p1();
//
//    @JsonProperty("sub7p1code")
//    String getSub7p1code();
//
//    @JsonProperty("sub7p2")
//    String getSub7p2();
//
//    @JsonProperty("sub7p2code")
//    String getSub7p2code();
//
//    @JsonProperty("sub8p1")
//    String getSub8p1();
//
//    @JsonProperty("sub8p1code")
//    String getSub8p1code();
//
//    @JsonProperty("sub8p2")
//    String getSub8p2();
//
//    @JsonProperty("sub8p2code")
//    String getSub8p2code();
//
//    @JsonProperty("sub9p1")
//    String getSub9p1();
//
//    @JsonProperty("sub9p1code")
//    String getSub9p1code();
//
//    @JsonProperty("sub9p2")
//    String getSub9p2();
//
//    @JsonProperty("sub9p2code")
//    String getSub9p2code();
//
//    @JsonProperty("sub10p1")
//    String getSub10p1();
//
//    @JsonProperty("sub10p1code")
//    String getSub10p1code();
//
//    @JsonProperty("sub10p2")
//    String getSub10p2();
//
//    @JsonProperty("sub10p2code")
//    String getSub10p2code();
//
//    @JsonProperty("sub11p1")
//    String getSub11p1();
//
//    @JsonProperty("sub11p1code")
//    String getSub11p1code();
//
//    @JsonProperty("sub11p2")
//    String getSub11p2();
//
//    @JsonProperty("sub11p2code")
//    String getSub11p2code();
//
//    @JsonProperty("sub12p1")
//    String getSub12p1();
//
//    @JsonProperty("sub12p1code")
//    String getSub12p1code();
//
//    @JsonProperty("sub12p2")
//    String getSub12p2();
//
//    @JsonProperty("sub12p2code")
//    String getSub12p2code();
//
//    @JsonProperty("paper1int")
//    String getPaper1int();
//
//    @JsonProperty("paper1th")
//    String getPaper1th();
//
//    @JsonProperty("paper1pr")
//    String getPaper1pr();
//
//    @JsonProperty("paper2int")
//    String getPaper2int();
//
//    @JsonProperty("paper2th")
//    String getPaper2th();
//
//    @JsonProperty("paper2pr")
//    String getPaper2pr();
//
//    @JsonProperty("paper3int")
//    String getPaper3int();
//
//    @JsonProperty("paper3th")
//    String getPaper3th();
//
//    @JsonProperty("paper3pr")
//    String getPaper3pr();
//
//    @JsonProperty("paper4int")
//    String getPaper4int();
//
//    @JsonProperty("paper4th")
//    String getPaper4th();
//
//    @JsonProperty("paper4pr")
//    String getPaper4pr();
//
//    @JsonProperty("paper5int")
//    String getPaper5int();
//
//    @JsonProperty("paper5th")
//    String getPaper5th();
//
//    @JsonProperty("paper5pr")
//    String getPaper5pr();
//
//    @JsonProperty("paper6int")
//    String getPaper6int();
//
//    @JsonProperty("paper6th")
//    String getPaper6th();
//
//    @JsonProperty("paper6pr")
//    String getPaper6pr();
//
//    @JsonProperty("paper7int")
//    String getPaper7int();
//
//    @JsonProperty("paper7th")
//    String getPaper7th();
//
//    @JsonProperty("paper7pr")
//    String getPaper7pr();
//
//    @JsonProperty("paper8th")
//    String getPaper8th();
//
//    @JsonProperty("paper8pr")
//    String getPaper8pr();
//
//    @JsonProperty("semresult")
//    String getSemresult();
//
//    @JsonProperty("grandtotal")
//    String getGrandtotal();
//
//    @JsonProperty("sempercent")
//    String getSempercent();
//
//    @JsonProperty("semgrade")
//    String getSemgrade();
//
//    @JsonProperty("semsgpa")
//    String getSemsgpa();
//
//    @JsonProperty("sempromotion")
//    String getSempromotion();
//
//    @JsonProperty("result_date")
//    String getResult_date();
//
//    @JsonProperty("exam_commensment")
//    String getExam_commensment();
//
//    @JsonProperty("examform_status")
//    String getExamform_status();
//
//    @JsonProperty("result_status")
//    String getResult_status();

    @JsonProperty("paymentStatus")
    String getPayment_status();

    @JsonProperty("paymentAmount")
    String getPayment_amount();

    @JsonProperty("paymentDate")
    String getPayment_date();

    @JsonProperty("referance_txn_id")
    String getReferance_txn_id();

    @JsonProperty("response_txn_id")
    String getResponse_txn_id();
//
//    @JsonProperty("payment_flag_addon")
//    String getPayment_flag_addon();
//
//    @JsonProperty("payment_amount_addon")
//    String getPayment_amount_addon();
//
//    @JsonProperty("payment_status_addon")
//    String getPayment_status_addon();
//
//    @JsonProperty("payment_date_addon")
//    String getPayment_date_addon();
//
//    @JsonProperty("referance_txn_id_addon")
//    String getReferance_txn_id_addon();
//
//    @JsonProperty("response_txn_id_addon")
//    String getResponse_txn_id_addon();
//
//    @JsonProperty("rejection_reason")
//    String getRejection_reason();
//
//    @JsonProperty("approve_flag")
//    String getApprove_flag();
//
//    @JsonProperty("student_id1")
//    String getStudent_id1();
//
//    @JsonProperty("app_date")
//    String getApp_date();
//
//    @JsonProperty("approved_date")
//    String getApproved_date();
//
//    @JsonProperty("rejected_date")
//    String getRejected_date();
//
//    @JsonProperty("paymentupdated")
//    String getPaymentupdated();
//
//    @JsonProperty("steps")
//    String getSteps();
//
//    @JsonProperty("exam_type")
//    String getExam_type();
//
//    @JsonProperty("honours")
//    String getHonours();
//
//    @JsonProperty("latefineremark")
//    String getLatefineremark();
}
