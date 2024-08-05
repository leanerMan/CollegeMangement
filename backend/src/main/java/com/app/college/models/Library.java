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
@Table(name = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "bill_no", length = 20, nullable = false)
	private String billNo;
	
	@Column(length = 50)
	private String title;
	
	@Column(length = 50, nullable = false)
	private String subject;
	
	@Column(length = 50, nullable = false)
	private String publisher;
	
	@Column(length = 50, nullable = false)
	private String department;
	
	@Column(name = "asset_type", length = 50, nullable = false)
	private String assetType;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "purchase_date", nullable = false)
	private LocalDate purchaseDate;
	
	@Column(length = 50, nullable = false)
	private double price;
	
	@Column(length = 2, nullable = false)
	private String status;
	
	@Column(length = 100, nullable = false)
	private String details;
	
	@JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
	@Column(name = "library_gen_date")
	private LocalDateTime libraryGenDate;
	
	@PrePersist
    public void prePersist() {
        if (this.libraryGenDate == null) {
            this.libraryGenDate = LocalDateTime.now();
        }
    }
}
