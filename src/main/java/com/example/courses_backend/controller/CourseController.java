package com.example.courses_backend.controller;

import com.example.courses_backend.model.Course;
import com.example.courses_backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    //  Create a new course
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        List<Course> validPrereqs = new ArrayList<>();
        for (Course prereq : course.getPrerequisites()) {
            Course existing = courseRepository.findById(prereq.getCourseId()).orElse(null);
            if (existing == null) {
                return ResponseEntity.badRequest()
                        .body("Invalid prerequisite ID: " + prereq.getCourseId());
            }
            validPrereqs.add(existing);
        }
        course.setPrerequisites(validPrereqs);
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    //  Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    //  Get single course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable String id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Delete a course (only if not a prerequisite elsewhere)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        // check if used as a prerequisite
        List<Course> allCourses = courseRepository.findAll();
        for (Course c : allCourses) {
            if (c.getPrerequisites().contains(course)) {
                return ResponseEntity.status(409)
                        .body("Cannot delete. Course is a prerequisite for: " + c.getCourseId());
            }
        }

        courseRepository.delete(course);
        return ResponseEntity.ok("Course deleted successfully");
    }
}

