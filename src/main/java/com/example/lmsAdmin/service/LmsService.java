package com.example.lmsAdmin.service;


import com.example.lmsAdmin.dto.*;
import com.example.lmsAdmin.entity.*;

import com.example.lmsAdmin.respository.CourseRepository;
import com.example.lmsAdmin.respository.EnrollmentRepository;
import com.example.lmsAdmin.respository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LmsService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;


    @Value("${cloud.aws.s3.bucket-name}") 
    private String bucketName;
    
    
    private CourseDTO mapToCourseDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setKeyOutComes(course.getKeyOutComes());
        dto.setDuration(course.getDuration());
        dto.setLevel(course.getLevel());

        if (course.getTechnologies() != null) {
            dto.setTechnologies(course.getTechnologies().stream()
                    .map(tech -> tech.getTechnologyName()) 
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    

    public CourseDTO saveCourse(CourseDTO courseDto) {
    	
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setKeyOutComes(courseDto.getKeyOutComes());
        course.setDuration(courseDto.getDuration());
        course.setLevel(courseDto.getLevel());

        if (courseDto.getTechnologies() != null) {
            List<Technologies> techList = courseDto.getTechnologies().stream()
                .map(techName -> {
                    Technologies tech = new Technologies();
                    tech.setTechnologyName(techName);
                    tech.setCourse(course); 
                    return tech;
                })
                .collect(Collectors.toList());
            course.setTechnologies(techList);
        }

        Course saved = courseRepository.save(course);
        return mapToCourseDTO(saved);
    }
    
    

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(this::mapToCourseDTO).collect(Collectors.toList());
    }

    public void deleteCourse(Long id) { 
    	courseRepository.deleteById(id); 
    	
    }
    
    
    

    public EnrollmentDTO addEnrollment(EnrollmentRequestDTO request) {
    	
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentName(request.getStudentName());
        enrollment.setCollegeName(request.getCollegeName());
        enrollment.setEmail(request.getEmail());
        enrollment.setPhoneNo(request.getPhoneNo());
        enrollment.setYear(request.getYear());

        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + request.getCourseId()));
            enrollment.setCourse(course);
        }

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToEnrollmentDTO(saved);
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
        		.stream()
        		.map(this::mapToEnrollmentDTO)
        		.collect(Collectors.toList());
    }




    private EnrollmentDTO mapToEnrollmentDTO(Enrollment e) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(e.getId());
        dto.setStudentName(e.getStudentName());
        dto.setCollegeName(e.getCollegeName());
        dto.setEmail(e.getEmail());
        if (e.getCourse() != null) 
        	dto.setCourseTitle(e.getCourse().getTitle());
        return dto;
    }

}