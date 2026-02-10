package com.example.lmsAdmin.respository;

import com.example.lmsAdmin.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Enrollment details save mariyu retrieve cheyadaniki
}