package com.edielson.forum.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edielson.forum.dto.UserDTO;
import com.edielson.forum.entities.Role;
import com.edielson.forum.entities.User;
import com.edielson.forum.repositories.UserRepository;
import com.edielson.forum.security.exceptions.ValidationException;
import com.edielson.forum.services.UserRegisterService;
import com.edielson.forum.services.exceptions.DataBaseException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private UserRepository repository;

    @Override
    public User create(UserDTO userDTO) {
        User user = fromDto(userDTO);
        existsByEmail(user);
        existsByName(user);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private User fromDto(UserDTO userDTO) {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        return new User(null, userDTO.name(), userDTO.email(), userDTO.password(), role);
    }
    
    private void existsByEmail(User user) {
        boolean existEmail = repository.existsByEmail(user.getEmail());
        if (existEmail) {
            throw new ValidationException("Email já cadastrado");
        }
    }

    private void existsByName(User user) {
        boolean existName = repository.existsByName(user.getName());
        if (existName) {
            throw new ValidationException("Nome já cadastrado");
        }
    }
}