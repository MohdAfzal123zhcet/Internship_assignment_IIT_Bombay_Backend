package com.example.courses_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course {

        @Id
        private String courseId;

        private String title;
        private String description;

        @ManyToMany
        private List<Course> prerequisites;
    }

