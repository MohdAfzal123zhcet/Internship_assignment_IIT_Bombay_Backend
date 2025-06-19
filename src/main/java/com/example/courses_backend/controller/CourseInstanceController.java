package com.example.courses_backend.controller;

import com.example.courses_backend.model.Course;
import com.example.courses_backend.model.CourseInstance;
import com.example.courses_backend.repository.CourseInstanceRepository;
import com.example.courses_backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/instances")
@RequiredArgsConstructor
public class CourseInstanceController {

    private final CourseInstanceRepository instanceRepository;
    private final CourseRepository courseRepository;

    //  Create a course instance
    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody CourseInstance instance) {
        String courseId = instance.getCourse().getCourseId();
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return ResponseEntity.badRequest().body("Invalid course ID: " + courseId);
        }
        instance.setCourse(course);
        instanceRepository.save(instance);
        return ResponseEntity.ok(instance);
    }

    //  List all instances for a year and semester
    @GetMapping("/{year}/{semester}")
    public List<CourseInstance> getInstancesByYearSemester(@PathVariable int year, @PathVariable int semester) {
        List<CourseInstance> all = instanceRepository.findAll();
        List<CourseInstance> filtered = new ArrayList<>();
        for (CourseInstance i : all) {
            if (i.getYear() == year && i.getSemester() == semester) {
                filtered.add(i);
            }
        }
        return filtered;
    }

    // Get a specific course instance
    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> getCourseInstance(@PathVariable int year,
                                               @PathVariable int semester,
                                               @PathVariable String courseId) {
        List<CourseInstance> all = instanceRepository.findAll();
        for (CourseInstance i : all) {
            if (i.getYear() == year && i.getSemester() == semester
                    && i.getCourse().getCourseId().equals(courseId)) {
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //  Delete a course instance
    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteInstance(@PathVariable int year,
                                            @PathVariable int semester,
                                            @PathVariable String courseId) {
        List<CourseInstance> all = instanceRepository.findAll();
        for (CourseInstance i : all) {
            if (i.getYear() == year && i.getSemester() == semester
                    && i.getCourse().getCourseId().equals(courseId)) {
                instanceRepository.delete(i);
                return ResponseEntity.ok("Instance deleted successfully");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
