package com.edielson.forum.dto;

import com.edielson.forum.entities.Course;

public record CourseResponseDTO(Long id, String name) {
    
    public CourseResponseDTO(Course course) {
        this(course.getId(), course.getName());
    }
}