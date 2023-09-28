package com.edielson.forum.dto;

import com.edielson.forum.entities.Category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    
    @NotBlank(message = "{name.obrigatorio}") 
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{name.letras}")
    String name, 
    
    @NotNull @Valid 
    Category category) {
}