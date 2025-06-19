package com.example.courses_backend.repository;

import com.example.courses_backend.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
}
