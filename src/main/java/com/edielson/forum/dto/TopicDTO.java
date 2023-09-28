package com.edielson.forum.dto;

import com.edielson.forum.entities.Course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDTO(

    @NotBlank(message = "{titulo.obrigatorio}")
    String titulo,

    @NotBlank(message = "{message.obrigatorio}")
    String message,

    @NotNull @Valid
    Course course) { 
}