package com.app.college.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.StaffDtoRequest;
import com.app.college.models.Staff;

import jakarta.validation.Valid;

public interface StaffService {

	Staff createStaffs(@Valid StaffDtoRequest staffDtoReq);

	Staff updateStaffById(Long id, @Valid StaffDtoRequest staffDtoReq);

	String deleteStaffById(Long id);

	Staff getStaffById(Long id);

	Page<Staff> getAllStaff(Pageable pageable);

	Staff createStaffsWithPhoto(@Valid StaffDtoRequest staffDtoReq, MultipartFile profilePhotoFile);

	Staff updateStaffByIdWithPhoto(Long id, @Valid StaffDtoRequest staffDtoReq, MultipartFile profilePhotoFile);

	Page<Staff> getStaffBySearch(String search, Pageable pageable);

}
