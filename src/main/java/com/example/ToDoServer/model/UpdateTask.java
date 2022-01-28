package com.example.ToDoServer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class UpdateTask {
    private String username;
    private String task;
    private int index;

    public UpdateTask(String username, String task, int index) {
        this.username = username;
        this.task = task;
        this.index = index;
    }
}
