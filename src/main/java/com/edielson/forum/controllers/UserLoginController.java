package com.edielson.forum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edielson.forum.dto.AuthenticationUser;
import com.edielson.forum.entities.User;
import com.edielson.forum.security.TokenJWT;
import com.edielson.forum.security.TokenService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class UserLoginController {
    
    private AuthenticationManager manager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid AuthenticationUser user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        var authentication = manager.authenticate(authenticationToken);
        
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}