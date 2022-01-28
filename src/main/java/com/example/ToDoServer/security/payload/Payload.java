package com.example.ToDoServer.security.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Payload {

    @Builder
    public Payload(Long id, String secret, String username) {
        this.id = id;
        this.secret = secret;
        this.username = username;
    }

    private final Long id;
    private final String secret;
    private final String username;

}
