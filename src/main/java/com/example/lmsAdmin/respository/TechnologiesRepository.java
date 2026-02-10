package com.example.lmsAdmin.respository;

import com.example.lmsAdmin.entity.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies, Long> {
    // Course ki sanbandhinchina technologies save cheyadaniki
}