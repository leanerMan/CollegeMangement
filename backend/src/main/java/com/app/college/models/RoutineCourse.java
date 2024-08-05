package com.app.college.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routine_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Course name cannot be empty")
    @Size(max = 20, message = "Course name must be less than or equal to 20 characters")
    @Column(name = "course", length = 20, nullable = false)
    private String course;

    @Size(max = 50, message = "Description must be less than or equal to 50 characters")
    @Column(name = "dscr", length = 50)
    private String dscr;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoutineTiming> routineTimings;
}
