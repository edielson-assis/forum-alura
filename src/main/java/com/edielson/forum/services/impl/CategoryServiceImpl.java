package com.edielson.forum.services.impl;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edielson.forum.dto.CategoryDTO;
import com.edielson.forum.dto.CategoryResponseDTO;
import com.edielson.forum.entities.Category;
import com.edielson.forum.repositories.CategoryRepository;
import com.edielson.forum.security.exceptions.ValidationException;
import com.edielson.forum.services.CategoryService;
import com.edielson.forum.services.exceptions.DataBaseException;
import com.edielson.forum.services.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Category category = fromDto(categoryDTO);
        existsByName(category);
        return repository.save(category);
    }

    @Override
    public Page<CategoryResponseDTO> findAll(Pageable pageable) {
        Page<Category> page = repository.findAll(pageable);
        return page.map(CategoryResponseDTO::new);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Categoria não encotrado. Id invalido: " + id));
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
    public Category update(Long id, CategoryDTO categoryDTO) {
        Category category = findById(id);
        try {
            updateData(category, categoryDTO);
            return repository.save(category);
        } catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Category fromDto(CategoryDTO categoryDTO) {
        return new Category(null, categoryDTO.name());
    }

    private void updateData(Category category, CategoryDTO categoryDTO) {
        category.setName(categoryDTO.name());
    }
    
    private void existsByName(Category category) {
        boolean existName = repository.existsByName(category.getName());
        if (existName) {
            throw new ValidationException("Categoria já cadastrada");
        }
    } 
}