package com.edielson.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edielson.forum.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    boolean existsByName(String nome);
}