package com.app.college.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    private Category category;
	
	@Column(name = "memo_no", length = 50, nullable = false)
	private String memoNo;
	
	@ManyToOne
	private Department department;
	
	@Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false, length = 500)
    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    @Column(name = "date_posted", nullable = false)
    private LocalDateTime datePosted;
    
    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    @Column(name = "publish_date", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "posted_by", nullable = false, length = 50)
    private String postedBy;
    
    @Column(name = "signed_by", length = 50)
    private String signedBy;

    @Column(length = 100)
    private String attachment;

    @Column(length = 20, nullable = false)
    private String status;
}
