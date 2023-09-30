package com.edielson.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CategoryIdDTO(
    
    @NotNull
    Long id,
    
    @NotBlank(message = "{name.obrigatorio}") 
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{name.letras}")
    String name) {
}