package com.app.college.mapper;

import org.springframework.stereotype.Component;

import com.app.college.DTO.StaffDtoRequest;
import com.app.college.models.Staff;
import com.app.college.util.DateTimeGenerator;

@Component
public class StaffMapper {

	public Staff toEntity(StaffDtoRequest staffDtoRequest) {
		Staff staff = new Staff();
		staff.setFirstName(staffDtoRequest.getFirstName());
		staff.setLastName(staffDtoRequest.getLastName());
		staff.setGender(staffDtoRequest.getGender());
		staff.setMobile(staffDtoRequest.getMobile());
		staff.setDesignation(staffDtoRequest.getDesignation());
		staff.setDepartments(staffDtoRequest.getDepartments());
		staff.setEmail(staffDtoRequest.getEmail());
//		if (staffDtoRequest.getJoiningDate() != null) {
//	        LocalDate localDate = LocalDate.parse(
//	            staffDtoRequest.getJoiningDate(),
//	            DateTimeFormatter.ofPattern("dd-MM-yyyy")
//	        );
//	        staff.setJoiningDate(localDate);
//	    } else {
	    	staff.setJoiningDate(staffDtoRequest.getJoiningDate());
//	    }
		staff.setProfilePhoto(staffDtoRequest.getProfilePhoto());
		staff.setAddress(staffDtoRequest.getAddress());
		staff.setEducation(staffDtoRequest.getEducation());
		staff.setProfileCreatedAt(DateTimeGenerator.getAutoLocalDateTime());
		return staff;

	}
	
	public Staff updateToEntity(Long id,StaffDtoRequest staffDtoRequest) {
		Staff staff = new Staff();
		staff.setId(id);
		staff.setFirstName(staffDtoRequest.getFirstName());
		staff.setLastName(staffDtoRequest.getLastName());
		staff.setGender(staffDtoRequest.getGender());
		staff.setMobile(staffDtoRequest.getMobile());
		staff.setDesignation(staffDtoRequest.getDesignation());
		staff.setDepartments(staffDtoRequest.getDepartments());
		staff.setEmail(staffDtoRequest.getEmail());
//		LocalDate localDate = LocalDate.parse(staffDtoRequest.getJoiningDate(),
//		DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		staff.setJoiningDate(staffDtoRequest.getJoiningDate());
		staff.setProfilePhoto(staffDtoRequest.getProfilePhoto());
		staff.setAddress(staffDtoRequest.getAddress());
		staff.setEducation(staffDtoRequest.getEducation());
		staff.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
		return staff;

	}

}
