package com.edielson.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edielson.forum.dto.TopicDTO;
import com.edielson.forum.dto.TopicResponseDTO;
import com.edielson.forum.dto.TopicUpdateDTO;
import com.edielson.forum.entities.Topic;

public interface TopicService {
    
    Topic create(TopicDTO topicDTO);

    Page<TopicResponseDTO> findAll(Pageable pageable);

    Topic findById(Long id);

    void delete(Long id);

    Topic update(Long id, TopicUpdateDTO topicUpdateDTO);
}