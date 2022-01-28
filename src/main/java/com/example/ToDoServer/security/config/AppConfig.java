package com.example.ToDoServer.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {

    private final String secretKey = "superapkatodo";
    private final String tokenPrefix = "Bearer ";

}
