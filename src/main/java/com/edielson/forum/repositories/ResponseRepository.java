package com.edielson.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.Topic;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findByTopic(Topic topic);
}