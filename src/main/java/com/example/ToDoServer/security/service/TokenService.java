package com.example.ToDoServer.security.service;

import com.example.ToDoServer.document.User;
import com.example.ToDoServer.security.payload.Payload;

public interface TokenService {

    String generateToken(User payload);
    Payload getPayload(String token);

}
