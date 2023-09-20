package com.edielson.forum.services.ipml;

import org.springframework.stereotype.Service;

import com.edielson.forum.dto.UserDTO;
import com.edielson.forum.entities.User;
import com.edielson.forum.repositories.UserRepository;
import com.edielson.forum.security.exceptions.ValidationException;
import com.edielson.forum.services.UserRegisterService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRegisterServiceIpml implements UserRegisterService {

    private UserRepository repository;

    @Override
    public User create(UserDTO userDTO) {
        User user = fromDto(userDTO);
        existsByEmail(user);
        return repository.save(user);
    }

    private User fromDto(UserDTO userDTO) {
        return new User(null, userDTO.name(), userDTO.email(), userDTO.password());
    }
    
    private void existsByEmail(User email) {
        boolean existeCargo = repository.existsByEmail(email.getEmail());
        if (existeCargo) {
            throw new ValidationException("Email já cadastrado");
        }
    }
}