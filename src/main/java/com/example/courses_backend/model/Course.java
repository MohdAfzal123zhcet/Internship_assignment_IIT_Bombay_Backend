package com.example.courses_backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
        private List<Course> prerequisites = new ArrayList<>(); // <-- fix here
}
