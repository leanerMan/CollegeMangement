package com.app.college.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibraryDto {

		private long id;
	
		@NotBlank(message = "billNo cannot be blank")
	    @Size(max = 20,message = "billNo size not more then 20")
	    private String billNo;

	    @Size(max = 50,message = "title size not more then 50")
	    private String title;

	    @NotBlank(message = "subject cannot be blank")
	    @Size(max = 50,message = "subject size not more then 50")
	    private String subject;

	    @NotBlank(message = "publisher cannot be blank")
	    @Size(max = 50,message = "publisher size not more then 50")
	    private String publisher;

	    @NotBlank(message = "department cannot be blank")
	    @Size(max = 50,message = "department size not more then 50")
	    private String department;

	    @NotBlank(message = "assetType cannot be blank")
	    @Size(max = 50,message = "assetType size not more then 50")
	    private String assetType;

	    @JsonFormat(pattern = "dd-MM-yyyy")
	    @NotNull(message = "purchaseDate must not be null")
	    private LocalDate purchaseDate;

	    @NotNull(message = "price must not be null")
	    private double price;

	    @NotBlank(message = "status cannot be blank")
	    @Size(max = 2, message = "status size not more then 2")
	    private String status;

	    @NotBlank(message = "details cannot be blank")
	    @Size(max = 100, message = "details size not more then 100")
	    private String details;
	    
	    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
		private LocalDateTime libraryGenDate;
}
