package com.example.ToDoServer.services;


import com.example.ToDoServer.exception.ResourceNotFoundException;
import com.example.ToDoServer.model.NewUser;
import com.example.ToDoServer.document.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
     List<User> getAllUsers();
     User getUser(String username) throws ResourceNotFoundException;
     User getUserWithToken(HttpServletRequest request) throws ResourceNotFoundException;
     User insertUser(NewUser newUser);
}
