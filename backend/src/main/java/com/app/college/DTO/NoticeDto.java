package com.app.college.DTO;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoticeDto {

	private Long id;
	
	@NotNull(message = "Category is mandatory")
	private Long categoryId;
	
	private String category;

	private String memoNo;

    private Long departmentId;
    
    private String department;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 500, message = "Content must be less than 500 characters")
    private String content;
 
    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    private LocalDateTime datePosted;
    
    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    @NotNull(message = "PublishDate is mandatory")
    private LocalDateTime publishDate;

    @NotBlank(message = "Posted by is mandatory")
    @Size(max = 50, message = "Posted by must be less than 50 characters")
    private String postedBy;
    
    @Size(max = 50, message = "signedBy must be less than 50 characters")
    private String signedBy;

    private String attachment;
    
    @Pattern(regexp = "^(?:ACTIVE|INACTIVE)$", message = "Status must be either ACTIVE or INACTIVE")
    private String status;
}
