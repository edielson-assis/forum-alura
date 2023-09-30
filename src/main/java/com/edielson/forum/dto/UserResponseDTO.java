package com.edielson.forum.dto;

import com.edielson.forum.entities.User;

public record UserResponseDTO(Long id, String name) {
    
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName());
    }
}