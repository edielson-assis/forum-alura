package com.edielson.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ResponseDTO(
    
    @NotBlank(message = "{message.obrigatorio}")
    String message,

    @NotNull
    @Pattern(regexp = "^[0-9]+$", message = "{topic.numeros}") 
    Long topic) {   
}