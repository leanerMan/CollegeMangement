package com.app.college.service;

import java.util.List;

import com.app.college.models.Category;

import jakarta.validation.Valid;

public interface CategoryService {

	Category createCategory(@Valid Category category);

	List<Category> getAllCategory();

	String deleteCategoryById(Long id);

}
