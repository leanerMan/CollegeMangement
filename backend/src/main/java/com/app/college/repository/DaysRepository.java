package com.app.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.college.models.Days;

public interface DaysRepository extends JpaRepository<Days, Long> {

}
