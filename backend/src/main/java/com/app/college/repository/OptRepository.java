package com.app.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.college.models.OtpData;

public interface OptRepository extends JpaRepository<OtpData, String> {

}
