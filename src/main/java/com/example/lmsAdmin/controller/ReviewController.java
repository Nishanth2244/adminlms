package com.example.lmsAdmin.controller;

import com.example.lmsAdmin.dto.ReviewDTO;
import com.example.lmsAdmin.entity.Reviews;
import com.example.lmsAdmin.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired private ReviewService reviewService;

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ReviewDTO addReview(
    		@RequestParam(name = "name", required = true) String name,
    		@RequestParam(name = "place", required = true) String place,
    		@RequestParam(name = "role", required = true) String role,
    		@RequestParam(name = "feedback", required = true) String feedback,
    		@RequestParam(name = "video", required = true) MultipartFile video

            ) throws IOException {

        return reviewService.addReview(name, place, role, feedback, video);
    }

    @GetMapping("/all")
    public List<ReviewDTO> getAll() {
        return reviewService.getAllReviews();
    }
}