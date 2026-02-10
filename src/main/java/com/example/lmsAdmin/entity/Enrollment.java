package com.example.lmsAdmin.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Enrollment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String collegeName;
	private String studentName;
	private String email;
	private Long phoneNo;
	private Long year;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;

}
