package com.app.college.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpData {
	
	@Id
	@Column(length = 13)
	private String number;
	@Column(length = 5)
	private String otp;
	private Boolean verified;
	private LocalDateTime time;
	private String role;

}
