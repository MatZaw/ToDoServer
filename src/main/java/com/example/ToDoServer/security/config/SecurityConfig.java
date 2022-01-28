package com.example.ToDoServer.security.config;

import com.example.ToDoServer.repository.UserRepository;
import com.example.ToDoServer.security.filter.AuthFilter;
import com.example.ToDoServer.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final AppConfig appConfig;
    private final TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**","/api/users/register").permitAll()
                .anyRequest().authenticated();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(new AuthFilter(appConfig,
                                userRepository,
                                tokenService),
                        BasicAuthenticationFilter.class);

    }

}
