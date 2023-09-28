package com.edielson.forum.dto;

import java.time.Instant;

import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.User;

public record ResponseTopicDTO(User author, String message, Instant moment) {

    public ResponseTopicDTO(Response response) {
        this(response.getAuthor(), response.getMessage(), response.getMoment());
    }
}