package com.edielson.forum.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record TopicDTO(

    @NotBlank(message = "{titulo.obrigatorio}")
    String titulo,

    @NotBlank(message = "{message.obrigatorio}")
    String message,

    @Valid
    CourseTopicDTO course) { 
}