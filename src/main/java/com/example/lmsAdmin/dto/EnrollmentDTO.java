package com.example.lmsAdmin.dto;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private String studentName;
    private String collegeName;
    private String email;
    private String courseTitle; // Just the title instead of full Course object
}