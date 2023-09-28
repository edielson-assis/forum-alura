package com.edielson.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseDTO(
    
    @NotBlank(message = "{message.obrigatorio}")
    String message,

    @NotNull
    Long topic) {   
}