package com.example.ToDoServer.security.service;

import com.example.ToDoServer.model.Login;
import com.example.ToDoServer.security.payload.Token;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    Token login(Login login) throws UsernameNotFoundException;

}
