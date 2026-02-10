package com.example.lmsAdmin.respository;

import com.example.lmsAdmin.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Basic CRUD operations automatic ga vastayi
}