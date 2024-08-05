package com.app.college.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import com.app.college.models.idGenerator.CustomStringSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staffs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	@Id
	@GeneratedValue()
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String firstName;

	@Column(length = 100, nullable = false)
	private String lastName;

	@Column(length = 50, nullable = false)
	private String departments;

	@Column(length = 50, nullable = false)
	private String designation;

	@Column(length = 10, nullable = false)
	private String gender;

	@Column(length = 13, nullable = false)
	private String mobile;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 150, nullable = false)
	private String address;

	@Column(length = 150, nullable = false)
	private String education;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;

	@Column(name = "profile_photo_filename")
	private String profilePhoto;

	private LocalDateTime profileUpdatedDate;
	
	private LocalDateTime profileCreatedAt;

	@PrePersist
    public void prePersist() {
        if (this.profileCreatedAt == null) {
            this.profileCreatedAt = LocalDateTime.now();
        }
    }
}
