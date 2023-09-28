package com.edielson.forum.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edielson.forum.dto.CourseDTO;
import com.edielson.forum.dto.CourseResponseDTO;
import com.edielson.forum.entities.Course;
import com.edielson.forum.services.CourseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    
    private CourseService service;

    @Transactional
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Course> create(@Valid @RequestBody CourseDTO courseDTO) {
        Course course = service.create(courseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> findAll(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id) {
        Course course = service.findById(id);
        return ResponseEntity.ok().body(new CourseResponseDTO(course));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        service.update(id, courseDTO);
        return ResponseEntity.noContent().build();
    }
}