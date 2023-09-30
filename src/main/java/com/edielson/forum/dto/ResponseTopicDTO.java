package com.edielson.forum.dto;

import java.time.Instant;

import com.edielson.forum.entities.Response;

public record ResponseTopicDTO(UserResponseDTO author, String message, Instant moment) {

    public ResponseTopicDTO(Response response) {
        this(new UserResponseDTO(response.getAuthor()), response.getMessage(), response.getMoment());
    }
}