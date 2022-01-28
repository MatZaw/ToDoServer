package com.example.ToDoServer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class NewTask {

    private String username;
    private String task;

    public NewTask(String username, String task) {
        this.username = username;
        this.task = task;
    }
}
