package com.app.college.models;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50, nullable = false)
	private String department;
	
	@Column(name = "head_of_department", length = 50, nullable = false)
	private String headOfDepartment;
	
	@Column(length = 15)
	private String phone;
	
	@Column(length = 30)
	private String email;
	
	
	@Column(name = "department_start_date")
	private LocalDate departmentStartDate;
	
	@Column(name = "student_capacity", length = 5)
	private int studentCapacity;
	
	@Column(length = 200)
	private String details;
	
	@JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
	@Column(name = "department_gen_date")
	private LocalDateTime departmentGenDate;
	
	@PrePersist
    public void prePersist() {
        if (this.departmentGenDate == null) {
            this.departmentGenDate = LocalDateTime.now();
        }
    }
	
	public Department(Long departmentId) {
		this.id=departmentId;
	}
}
