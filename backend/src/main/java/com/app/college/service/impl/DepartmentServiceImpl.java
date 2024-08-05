package com.app.college.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.DepartmentDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Department;
import com.app.college.repository.DepartmentRepository;
import com.app.college.service.DepartmentService;

import jakarta.validation.Valid;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department createDepartment(@Valid DepartmentDto departmentDtoReq) {
			Department department = new Department();
        BeanUtils.copyProperties(departmentDtoReq, department);
        return departmentRepository.save(department);
	}

	@Override
	public Page<Department> getAllDepartment(Pageable pageable) {
		 return departmentRepository.findAll(pageable);
	}

	@Override
	public Department updateStudentById(Long id, @Valid DepartmentDto departmentDtoReq) {
		if (departmentRepository.existsById(id)) {
			Department department = new Department();
	        BeanUtils.copyProperties(departmentDtoReq, department);
	        department.setId(id);
			return departmentRepository.save(department);
		}
		throw new ConfigDataNotFound("No Department found with id: " + id);
	}

	@Override
	public String deleteDepartmentById(Long id) {
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
			return "Department deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Department found with id: " + id);
	}

	@Override
	public Department getDepartmentById(Long id) {
		if (departmentRepository.existsById(id)) {
			 return departmentRepository.findById(id).get();
		}
		throw new ConfigDataNotFound("No Department found with id: " + id);
	}

	@Override
	public List<Department> getDepartmentBySearch(String search) {
		return departmentRepository.getDepartmentBySearch(search);
	}

}
