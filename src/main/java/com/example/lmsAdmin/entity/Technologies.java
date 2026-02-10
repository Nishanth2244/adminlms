package com.example.lmsAdmin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Technologies {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String technologyName;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

}
