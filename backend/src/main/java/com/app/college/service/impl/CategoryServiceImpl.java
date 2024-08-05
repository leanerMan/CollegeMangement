package com.app.college.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.college.models.Category;
import com.app.college.repository.CategoryRepository;
import com.app.college.service.CategoryService;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepo;

	public CategoryServiceImpl(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public Category createCategory(@Valid Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public String deleteCategoryById(Long id) {
		categoryRepo.deleteById(id);
		return "Deleted !!!";
	}
	
}
