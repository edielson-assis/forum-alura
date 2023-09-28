package com.edielson.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edielson.forum.dto.CourseDTO;
import com.edielson.forum.dto.CourseResponseDTO;
import com.edielson.forum.entities.Course;

public interface CourseService {
    
    Course create(CourseDTO courseDTO);

    Page<CourseResponseDTO> findAll(Pageable pageable);

    Course findById(Long id);

    void delete(Long id);

    Course update(Long id, CourseDTO courseDTO);
}