package com.app.college.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.DepartmentDto;
import com.app.college.models.Department;

import jakarta.validation.Valid;

public interface DepartmentService {

	Department createDepartment(@Valid DepartmentDto departmentDtoReq);

	Page<Department> getAllDepartment(Pageable pageable);

	Department updateStudentById(Long id, @Valid DepartmentDto departmentDtoReq);

	String deleteDepartmentById(Long id);

	Department getDepartmentById(Long id);

	List<Department> getDepartmentBySearch(String search);

}
