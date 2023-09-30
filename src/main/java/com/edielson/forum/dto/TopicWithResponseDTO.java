package com.edielson.forum.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.Topic;
import com.edielson.forum.entities.enums.StatusTopic;

public record TopicWithResponseDTO(Long id, String titulo, String message, Instant moment, StatusTopic status, UserResponseDTO author, CourseCategoryResponseDTO course, List<ResponseTopicDTO> responses) {
    
    public TopicWithResponseDTO(Topic topic) {
        this(topic.getId(), topic.getTitulo(), topic.getMessage(), topic.getMoment(), topic.getStatus(), new UserResponseDTO(topic.getAuthor()), new CourseCategoryResponseDTO(topic.getCourse()), fromDto(topic));
    }

    private static List<ResponseTopicDTO> fromDto(Topic topic) {
        List<Response> response = topic.getResponses();
        return response.stream().map(ResponseTopicDTO::new).collect(Collectors.toList());
    }
}