package com.example.lmsAdmin.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String name;
    private String place;
    private String role;
    private String feedback;
    private String videoUrl;
}