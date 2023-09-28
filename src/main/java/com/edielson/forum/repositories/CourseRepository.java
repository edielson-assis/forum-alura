package com.edielson.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edielson.forum.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
    boolean existsByName(String nome);
}