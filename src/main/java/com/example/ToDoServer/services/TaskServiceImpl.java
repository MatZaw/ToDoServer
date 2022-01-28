package com.example.ToDoServer.services;

import com.example.ToDoServer.exception.ResourceNotFoundException;
import com.example.ToDoServer.model.DeleteTask;
import com.example.ToDoServer.model.NewTask;
import com.example.ToDoServer.model.UpdateTask;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements  TaskService{

    private final UserRepository userRepository;

    @Override
    public List<String> getTasks(String username) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);

        return user.getTasks();
    }

    @Override
    public void removeTask(DeleteTask deleteTask) {

        User user = userRepository.findUserByUsername(deleteTask.getUsername())
                .orElseThrow(ResourceNotFoundException::new);

        user.getTasks().remove(deleteTask.getIndex());

        userRepository.save(user);
    }

    @Override
    public void insertTask(NewTask newTask) {

        User user = userRepository.findUserByUsername(newTask.getUsername())
                .orElseThrow(ResourceNotFoundException::new);

        user.getTasks().add(newTask.getTask());

        userRepository.save(user);

    }

    @Override
    public void updateTask(UpdateTask updateTask) {

        User user = userRepository.findUserByUsername(updateTask.getUsername())
                .orElseThrow(ResourceNotFoundException::new);

        user.getTasks().set(updateTask.getIndex(), updateTask.getTask());

        userRepository.save(user);

    }
}
