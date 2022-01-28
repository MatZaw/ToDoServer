package com.example.ToDoServer.controller;

import com.example.ToDoServer.model.NewUser;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<User> getUserByToken(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUserWithToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody NewUser newUser){
        return ResponseEntity.ok(userService.insertUser(newUser));
    }

}
