package com.example.lmsAdmin.dto;

import lombok.Data;

@Data
public class EnrollmentRequestDTO {
    private String studentName;
    private String collegeName;
    private String email;
    private Long phoneNo;
    private Long year;
    private Long courseId; 
}