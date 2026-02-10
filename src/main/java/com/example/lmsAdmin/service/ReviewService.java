package com.example.lmsAdmin.service;


import com.example.lmsAdmin.dto.ReviewDTO;
import com.example.lmsAdmin.entity.Reviews;

import com.example.lmsAdmin.respository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewsRepository reviewsRepository;
    
    @Autowired
    private S3Service s3Service;

    @Value("${cloud.aws.s3.bucket-name}") 
    private String bucketName;

    
    private ReviewDTO mapToDTO(Reviews review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setName(review.getName());
        dto.setPlace(review.getPlace());
        dto.setRole(review.getRole());
        dto.setFeedback(review.getFeedback());
        dto.setVideoUrl(review.getVideo()); 
        return dto;
    }

    public ReviewDTO addReview(String name, String place, String role, String feedback, MultipartFile video) throws IOException {

        String videoKey = s3Service.uploadReviewVideo(video);
        
        Reviews reviews = new Reviews();
        reviews.setName(name);
        reviews.setPlace(place);
        reviews.setRole(role);
        reviews.setFeedback(feedback);
        
        reviews.setVideo(videoKey);
        
        Reviews savedReview = reviewsRepository.save(reviews);
        return mapToDTO(savedReview);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewsRepository.findAll().stream()
                .map(review -> {
                    ReviewDTO dto = mapToDTO(review);
                    if (review.getVideo() != null) {
                        dto.setVideoUrl(s3Service.getFileUrl(review.getVideo()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
}