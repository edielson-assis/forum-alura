package com.edielson.forum.services;

import com.edielson.forum.dto.UserDTO;
import com.edielson.forum.entities.User;

public interface UserRegisterService {
    
    User create(UserDTO userDTO);

    void delete(Long id);
}