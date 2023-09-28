package com.edielson.forum.dto;

import com.edielson.forum.entities.Category;

public record CategoryResponseDTO(Long id, String name) {
    
    public CategoryResponseDTO(Category category) {
        this(category.getId(), category.getName());
    }
}