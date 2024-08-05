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
@Table(name = "holiday")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title", length = 20, nullable = false)
	private String title;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "holiday_type", length = 20, nullable = false)
	private String holidayType;
	
	@Column(name = "holiday_details", length = 150)
	private String holidayDetails;
	
	@JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
	@Column(name = "holiday_gen_date")
	private LocalDateTime holidayGenDate;
	
	@PrePersist
    public void prePersist() {
        if (this.holidayGenDate == null) {
            this.holidayGenDate = LocalDateTime.now();
        }
    }
}
