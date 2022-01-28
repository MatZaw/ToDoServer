package com.example.ToDoServer.controller;

import com.example.ToDoServer.model.DeleteTask;
import com.example.ToDoServer.model.NewTask;
import com.example.ToDoServer.model.UpdateTask;
import com.example.ToDoServer.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{username}")
    public ResponseEntity<List<String>> getTasks(@PathVariable("username") String username) {
        List<String> tasks = taskService.getTasks(username);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody DeleteTask deleteTask){
        taskService.removeTask(deleteTask);
    }

    @PostMapping("/insert")
    public void insertTask(@RequestBody NewTask newTask){
        taskService.insertTask(newTask);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody UpdateTask updateTask){
        taskService.updateTask(updateTask);
    }

}
