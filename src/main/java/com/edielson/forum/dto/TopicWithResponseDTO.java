package com.edielson.forum.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.edielson.forum.entities.Course;
import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.Topic;
import com.edielson.forum.entities.User;
import com.edielson.forum.entities.enums.StatusTopic;

public record TopicWithResponseDTO(Long id, String titulo, String message, Instant moment, StatusTopic status, User author, Course course, List<ResponseTopicDTO> responses) {
    
    public TopicWithResponseDTO(Topic topic) {
        this(topic.getId(), topic.getTitulo(), topic.getMessage(), topic.getMoment(), topic.getStatus(), topic.getAuthor(), topic.getCourse(), fromDto(topic));
    }

    private static List<ResponseTopicDTO> fromDto(Topic topic) {
        List<Response> response = topic.getResponses();
        return response.stream().map(ResponseTopicDTO::new).collect(Collectors.toList());
    }
}