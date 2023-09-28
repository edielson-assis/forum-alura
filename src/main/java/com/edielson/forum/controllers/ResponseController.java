package com.edielson.forum.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edielson.forum.dto.ResponseDTO;
import com.edielson.forum.dto.ResponseTopicDTO;
import com.edielson.forum.dto.ResponseUpdateDTO;
import com.edielson.forum.entities.Response;
import com.edielson.forum.services.ResponseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    
    private ResponseService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody ResponseDTO responseDTO) {
        Response response = service.create(responseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<ResponseTopicDTO>> findAll(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTopicDTO> findById(@PathVariable Long id) {
        Response response = service.findById(id);
        return ResponseEntity.ok().body(new ResponseTopicDTO(response));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}") 
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @RequestBody ResponseUpdateDTO responseUpdateDTO) {
        service.update(id, responseUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}