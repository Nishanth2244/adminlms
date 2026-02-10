package com.example.lmsAdmin.controller;

import com.example.lmsAdmin.dto.*;
import com.example.lmsAdmin.entity.*;
import com.example.lmsAdmin.service.LmsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LmsController {

    @Autowired 
    private LmsService lmsService;


    @PostMapping("/courses/add")
    public CourseDTO addCourse(@RequestBody CourseDTO courseDto) {
        return lmsService.saveCourse(courseDto);
    }

    @GetMapping("/courses/get")
    public List<CourseDTO> getCourses() {
        return lmsService.getAllCourses();
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        lmsService.deleteCourse(id);
    }

    

    @PostMapping("/enrollments")
    public EnrollmentDTO enroll(@RequestBody EnrollmentRequestDTO enrollmentRequest) {
         
    	EnrollmentDTO enrollmentDTO = lmsService.addEnrollment(enrollmentRequest);
    	
    	log.info("Student added");
    	return enrollmentDTO;
         
         
    }

    @GetMapping("/enrollments/getall")
    public List<EnrollmentDTO> getEnrollments() {
        return lmsService.getAllEnrollments();
    }
}