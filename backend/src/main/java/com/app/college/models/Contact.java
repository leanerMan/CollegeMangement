package com.app.college.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="contact_image_file_name")
	private String contactImageName;
	
	@Column(length = 100,nullable = false)
	private String name;
	
	@Column(length = 50,nullable = false)
	private String email;
	
	@Column(length = 13,nullable = false)
	private String mobile;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@Column(length = 200)
	private String address;
	
	private String note;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
