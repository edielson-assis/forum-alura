package com.edielson.forum.dto;

import jakarta.validation.constraints.NotBlank;

public record ResponseUpdateDTO(@NotBlank(message = "{message.obrigatorio}") String message) {}