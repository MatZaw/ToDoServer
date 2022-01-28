package com.example.ToDoServer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class DeleteTask {

    private String username;
    private int index;

    public DeleteTask(String username, int index) {
        this.username = username;
        this.index = index;
    }
}
