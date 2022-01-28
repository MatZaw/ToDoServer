package com.example.ToDoServer.security.service.impl;

import com.example.ToDoServer.document.User;
import com.example.ToDoServer.security.config.AppConfig;
import com.example.ToDoServer.security.payload.Payload;
import com.example.ToDoServer.security.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AppConfig appConfig;

    @Override
    public String generateToken(User payload) {

        return Jwts.builder()
                .setSubject(payload.getUsername())
                .setExpiration(Date.from(Instant.now().plusSeconds(86400)))
                .signWith(HS256, appConfig.getSecretKey().getBytes())
                .compact();

    }

    @Override
    public Payload getPayload(String token) {

        Claims body = Jwts.parser()
                .setSigningKey(appConfig.getSecretKey().getBytes())
                .parseClaimsJws(token)
                .getBody();

        return Payload.builder()
                .username(body.getSubject())
                .build();

    }
}
