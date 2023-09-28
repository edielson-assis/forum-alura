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

import com.edielson.forum.dto.TopicDTO;
import com.edielson.forum.dto.TopicResponseDTO;
import com.edielson.forum.dto.TopicWithResponseDTO;
import com.edielson.forum.dto.TopicUpdateDTO;
import com.edielson.forum.entities.Topic;
import com.edielson.forum.services.TopicService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    
    private TopicService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Topic> create(@Valid @RequestBody TopicDTO topicDTO) {
        Topic topic = service.create(topicDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> findAll(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicWithResponseDTO> findById(@PathVariable Long id) {
        Topic topic = service.findById(id);
        return ResponseEntity.ok().body(new TopicWithResponseDTO(topic));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @RequestBody TopicUpdateDTO topicUpdateDTO) {
        service.update(id, topicUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}