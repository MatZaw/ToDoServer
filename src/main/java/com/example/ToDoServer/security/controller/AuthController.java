package com.example.ToDoServer.security.controller;

import com.example.ToDoServer.model.Login;
import com.example.ToDoServer.security.payload.Token;
import com.example.ToDoServer.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Token> loginUser(@RequestBody Login login){
        return ResponseEntity.ok(authService.login(login));
    }

}


