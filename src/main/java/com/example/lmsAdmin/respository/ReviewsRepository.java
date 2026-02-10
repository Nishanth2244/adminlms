package com.example.lmsAdmin.respository;

import com.example.lmsAdmin.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    // Feedback mariyu S3 video URLs handle cheyadaniki
}