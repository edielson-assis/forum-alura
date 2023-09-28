package com.edielson.forum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    
    @NotBlank(message = "{name.obrigatorio}") 
    String name, 

    @NotBlank(message = "{email.obrigatorio}") 
    @Email(message = "{email.invalido}") 
    String email, 

    @NotBlank(message = "{password.obrigatorio}") 
    String password) {
}