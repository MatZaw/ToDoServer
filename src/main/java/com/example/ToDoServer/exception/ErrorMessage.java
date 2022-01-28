package com.example.ToDoServer.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Optional;

@Getter
public class ErrorMessage {

    private final Instant timestamp;
    private final Integer status;

    @JsonIgnore
    private final HttpStatus httpStatus;
    private final String error;
    private final String message;

    @Builder
    public ErrorMessage(String message, HttpStatus httpStatus) {

        this.timestamp = Instant.now();
        this.message = message;

        this.httpStatus = Optional.ofNullable(httpStatus).orElse(HttpStatus.OK);
        this.error = this.httpStatus.getReasonPhrase();
        this.status = this.httpStatus.value();

    }

    public ResponseEntity<ErrorMessage> returnError(){
        return new ResponseEntity<>(this,httpStatus);
    }

}
