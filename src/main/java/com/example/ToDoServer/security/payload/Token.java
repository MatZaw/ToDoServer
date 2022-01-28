package com.example.ToDoServer.security.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Token {

    private final String token;

    @Builder
    public Token(String token) {
        this.token = token;
    }

}
