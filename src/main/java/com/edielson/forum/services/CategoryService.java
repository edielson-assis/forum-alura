package com.edielson.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edielson.forum.dto.CategoryDTO;
import com.edielson.forum.dto.CategoryResponseDTO;
import com.edielson.forum.entities.Category;

public interface CategoryService {
    
    Category create(CategoryDTO categoryDTO);

    Page<CategoryResponseDTO> findAll(Pageable pageable);

    Category findById(Long id);

    void delete(Long id);

    Category update(Long id, CategoryDTO categoryDTO);
}