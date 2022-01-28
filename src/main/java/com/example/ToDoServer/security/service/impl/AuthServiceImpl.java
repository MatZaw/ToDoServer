package com.example.ToDoServer.security.service.impl;

import com.example.ToDoServer.exception.InvalidPasswordException;
import com.example.ToDoServer.exception.ResourceNotFoundException;
import com.example.ToDoServer.model.Login;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.security.payload.Token;
import com.example.ToDoServer.security.service.AuthService;
import com.example.ToDoServer.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    @Override
    public Token login(Login userLogin) throws ResourceNotFoundException, InvalidPasswordException {

        User userDetails = (User) userDetailsService.loadUserByUsername(userLogin.getUsername());

        if (!passwordEncoder.matches(userLogin.getPassword(),userDetails.getPassword())) {
            throw new InvalidPasswordException();
        }

        return new Token(tokenService.generateToken(userDetails));

    }

}
