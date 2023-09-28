package com.edielson.forum.services.impl;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edielson.forum.dto.CourseDTO;
import com.edielson.forum.dto.CourseResponseDTO;
import com.edielson.forum.entities.Course;
import com.edielson.forum.repositories.CourseRepository;
import com.edielson.forum.security.exceptions.ValidationException;
import com.edielson.forum.services.CourseService;
import com.edielson.forum.services.exceptions.DataBaseException;
import com.edielson.forum.services.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    @Override
    public Course create(CourseDTO courseDTO) {
        Course course = fromDto(courseDTO);
        existsByName(course);
        return repository.save(course);
    }

    @Override
    public Page<CourseResponseDTO> findAll(Pageable pageable) {
        Page<Course> page = repository.findAll(pageable);
        return page.map(CourseResponseDTO::new);
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> course = repository.findById(id);
        return course.orElseThrow(() -> new ObjectNotFoundException("Curso não encotrado. Id invalido: " + id));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) {
        Course course = findById(id);
        try {
            updateData(course, courseDTO);
            return repository.save(course);
        } catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Course fromDto(CourseDTO courseDTO) {
        return new Course(null, courseDTO.name(), courseDTO.category());
    }

    private void updateData(Course course, CourseDTO courseDTO) {
        course.setName(courseDTO.name());
    }
    
    private void existsByName(Course course) {
        boolean existName = repository.existsByName(course.getName());
        if (existName) {
            throw new ValidationException("Curso já cadastrado");
        }
    }    
}