package com.example.ToDoServer.security.filter;

import com.example.ToDoServer.exception.ResourceNotFoundException;
import com.example.ToDoServer.document.User;
import com.example.ToDoServer.repository.UserRepository;
import com.example.ToDoServer.security.config.AppConfig;
import com.example.ToDoServer.security.payload.Payload;
import com.example.ToDoServer.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final AppConfig appConfig;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String auth = request.getHeader(AUTHORIZATION);
            if(auth !=null && auth.startsWith(appConfig.getTokenPrefix())){
                String token = auth.substring(appConfig.getTokenPrefix().length());
                Payload payloadFromToken = tokenService.getPayload(token);
                User user = userRepository.findUserByUsername(payloadFromToken.getUsername())
                        .orElseThrow(ResourceNotFoundException::new);
                Collection<SimpleGrantedAuthority> authorities = user.getAuthorities();
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception exception) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
