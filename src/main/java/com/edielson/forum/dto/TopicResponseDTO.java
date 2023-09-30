package com.edielson.forum.dto;

import java.time.Instant;

import com.edielson.forum.entities.Topic;
import com.edielson.forum.entities.enums.StatusTopic;

public record TopicResponseDTO(Long id, String titulo, String message, Instant moment, StatusTopic status, UserResponseDTO author, CourseCategoryResponseDTO course) {
    
    public TopicResponseDTO(Topic topic) {
        this(topic.getId(), topic.getTitulo(), topic.getMessage(), topic.getMoment(), topic.getStatus(), new UserResponseDTO(topic.getAuthor()), new CourseCategoryResponseDTO(topic.getCourse()));
    }
}