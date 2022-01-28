package com.example.ToDoServer.security.permissions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    READ("READ", 1),
    WRITE("WRITE", 2),
    DELETE("DELETE", 4);

    private final String permission;
    private final int level;




}
