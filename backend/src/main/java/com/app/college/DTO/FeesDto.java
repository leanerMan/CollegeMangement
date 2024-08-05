package com.app.college.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;


@Data
public class FeesDto {

    private long id;

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Department name is required")
    private String departmentName;

    @NotBlank(message = "Fees type is required")
    private String feesType;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "collectionDate is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate collectionDate;

    @NotBlank(message = "Payment type is required")
    private String paymentType;

    private String invoiceNumber;

    @NotBlank(message = "Status is required")
    private String status;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Details are required")
    private String details;
}

