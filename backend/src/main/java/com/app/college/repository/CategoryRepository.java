package com.app.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.college.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
