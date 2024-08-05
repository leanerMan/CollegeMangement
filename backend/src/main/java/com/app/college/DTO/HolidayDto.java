package com.app.college.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HolidayDto {

	private long id;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,20}$", message = "Title must be alphanumeric and less than 20 characters")
    private String title;

    @NotNull(message = "Start date is required")
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @NotBlank(message = "Holiday type is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,20}$", message = "Holiday type must be alphanumeric and less than 20 characters")
    private String holidayType;

    @Size(min=0, max = 150, message = "holidayDetails must be less than 150 characters")
    private String holidayDetails;

    @JsonFormat(pattern = "dd-MM-yyyy::HH:mm:ss")
    private LocalDateTime holidayGenDate;
}
