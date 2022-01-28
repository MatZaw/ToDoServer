package com.example.ToDoServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String explanation) {
        super(explanation);
    }

    public ResourceConflictException() {
        super("Data conflict");
    }


}
