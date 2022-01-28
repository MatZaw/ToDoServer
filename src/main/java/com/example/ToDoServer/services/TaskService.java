package com.example.ToDoServer.services;

import com.example.ToDoServer.model.DeleteTask;
import com.example.ToDoServer.model.Login;
import com.example.ToDoServer.model.NewTask;
import com.example.ToDoServer.model.UpdateTask;

import java.util.List;

public interface TaskService {
    List<String> getTasks(String username);
    void removeTask(DeleteTask deleteTask);
    void insertTask(NewTask newTask);
    void updateTask(UpdateTask updateTask);
}
