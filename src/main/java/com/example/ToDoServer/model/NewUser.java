package com.example.ToDoServer.model;

import com.example.ToDoServer.security.permissions.Role;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class NewUser {

    private String username;
    private String password;
    private List<String> tasks;
    private Role role;

    public NewUser(String username,
                   String password,
                   List<String> tasks,
                   Role role) {
        this.username = username;
        this.password = password;
        this.tasks = tasks;
        this.role = role;
    }
}
