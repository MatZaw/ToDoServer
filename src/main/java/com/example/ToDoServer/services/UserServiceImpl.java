package com.example.ToDoServer.services;

import com.example.ToDoServer.exception.ResourceConflictException;
import com.example.ToDoServer.exception.ResourceNotFoundException;
import com.example.ToDoServer.model.NewUser;
import com.example.ToDoServer.repository.UserRepository;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.security.config.AppConfig;
import com.example.ToDoServer.security.payload.Payload;
import com.example.ToDoServer.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AppConfig appConfig;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User getUserWithToken(HttpServletRequest request) throws ResourceNotFoundException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if(authHeader !=null && authHeader.startsWith(appConfig.getTokenPrefix())) {
            String token = authHeader.substring(appConfig.getTokenPrefix().length());

            Payload payloadFromToken = tokenService.getPayload(token);
            return getUser(payloadFromToken.getUsername());
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public User insertUser(NewUser newUser){
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new ResourceConflictException();
        }
        return userRepository.insert(new User(
                newUser.getUsername(),
                passwordEncoder.encode(newUser.getPassword()),
                newUser.getTasks(),
                newUser.getRole()
        ));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
