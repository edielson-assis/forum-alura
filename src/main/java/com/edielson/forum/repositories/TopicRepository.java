package com.edielson.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edielson.forum.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitulo(String titulo);
}