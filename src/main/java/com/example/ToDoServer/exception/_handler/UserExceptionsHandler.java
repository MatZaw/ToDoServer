package com.example.ToDoServer.exception._handler;

import com.example.ToDoServer.exception.ErrorMessage;
import com.example.ToDoServer.exception.InvalidPasswordException;
import com.example.ToDoServer.exception.ResourceConflictException;
import com.example.ToDoServer.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class UserExceptionsHandler {

    @ExceptionHandler(value = {ResourceConflictException.class})
    public ResponseEntity<ErrorMessage> handleUserExistsException(ResourceConflictException ex){
        return ErrorMessage.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(Optional.ofNullable(ex.getMessage())
                        .orElse(ex.toString()))
                .build().returnError();
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleUserNotExistsException(ResourceNotFoundException ex){
        return ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(Optional.ofNullable(ex.getMessage())
                        .orElse(ex.toString()))
                .build().returnError();
    }

    @ExceptionHandler(value = {InvalidPasswordException.class})
    public ResponseEntity<ErrorMessage> handleInvalidPasswordException(InvalidPasswordException ex){
        return ErrorMessage.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(Optional.ofNullable(ex.getMessage())
                        .orElse(ex.toString()))
                .build().returnError();
    }

}
