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
@Table(name = "fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "roll_no", length = 20, nullable = false)
	private String rollNo;
	
	@Column(name = "student_name", length = 30, nullable = false)
	private String studentName;
	
	@Column(name = "department_name", length = 30, nullable = false)
	private String departmentName;
	
	@Column(name = "fees_type", length = 15, nullable = false)
	private String feesType;
	
	@Column(name = "duration", length = 15, nullable = false)
	private String duration;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "collection_date")
	private LocalDate collectionDate;
	
	@Column(name = "payment_type", length = 15, nullable = false)
	private String paymentType;
	
	@Column(name = "invoice_number", length = 15)
	private String invoiceNumber;
	
	@Column(name = "status", length = 10, nullable = false)
	private String status;
	
	@Column(name = "amount", length = 15, nullable = false)
	private double amount;
	
	@Column(name = "details", length = 50)
	private String details;
	
	@JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
	@Column(name = "fees_gen_date")
	private LocalDateTime feesGenDate;
	
	@PrePersist
    public void prePersist() {
        if (this.feesGenDate == null) {
            this.feesGenDate = LocalDateTime.now();
        }
    }
}
