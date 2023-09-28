package com.edielson.forum.dto;

import com.edielson.forum.entities.enums.StatusTopic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(@NotNull StatusTopic status) {}