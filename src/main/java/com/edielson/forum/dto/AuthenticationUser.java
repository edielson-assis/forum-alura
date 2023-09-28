package com.edielson.forum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationUser (

    @NotBlank(message = "{email.obrigatorio}") 
    @Email(message = "{email.invalido}") 
    String email, 

    @NotBlank(message = "{password.obrigatorio}") 
    String password) {
}