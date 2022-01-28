package com.example.ToDoServer.security.config;

import com.example.ToDoServer.model.NewUser;
import com.example.ToDoServer.security.permissions.Role;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.repository.UserRepository;
import com.example.ToDoServer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccessConfig {

    private final UserRepository userRepository;
    private final UserService userService;

    private static final String login = "admin";
    private static final String password = "admin";

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        Optional<User> admin = userRepository.findUserByUsername(login);

        if (admin.isEmpty()) {
            userService.insertUser(new NewUser(login,
                            password,
                            null,
                            Role.ADMIN));
        }

    }
}
