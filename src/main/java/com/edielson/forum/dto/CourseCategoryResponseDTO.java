package com.edielson.forum.dto;

import com.edielson.forum.entities.Course;

public record CourseCategoryResponseDTO(Long id, String name, CategoryResponseDTO category) {
    
    public CourseCategoryResponseDTO(Course course) {
        this(course.getId(), course.getName(), new CategoryResponseDTO(course.getCategory()));
    }
}