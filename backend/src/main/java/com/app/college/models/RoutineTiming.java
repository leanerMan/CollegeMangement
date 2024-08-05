package com.app.college.models;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routine_timing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineTiming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Start time cannot be null")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Size(max = 20, message = "Teacher name must be less than or equal to 20 characters")
    @Column(name = "teacher_name", length = 20)
    private String teacherName;

    @NotEmpty(message = "Subject cannot be empty")
    @Size(max = 50, message = "Subject must be less than or equal to 50 characters")
    @Column(name = "subject", length = 50)
    private String subject;
}
