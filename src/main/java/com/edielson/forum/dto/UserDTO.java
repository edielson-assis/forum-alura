package com.edielson.forum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    
    @NotBlank(message = "Nome não pode ser vázio") String name, 

    @NotBlank(message = "Email não pode ser vázio") 
    @Email(message = "Formato de email inválido") 
    String email, 

    @NotBlank(message = "Senha não pode ser vázio") 
    String password) {
}