package com.example.lmsAdmin.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> technologies;
    private String keyOutComes;
    private String duration;
    private String level;
}