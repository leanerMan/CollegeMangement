package com.app.college.service;

import java.security.InvalidKeyException;

import com.app.college.payload.request.LoginRequest;

public interface OtpService {


	public Boolean generateOtp(String contactPhone,String role) ;

	public LoginRequest varifyOtp(String contactPhone, String otp) throws InvalidKeyException;
	


}
