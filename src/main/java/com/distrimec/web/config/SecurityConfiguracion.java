package com.distrimec.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguracion {

     private final AuthenticationProvider authProvider;
    
     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(csrf -> 
                csrf
                .disable())
            .authorizeHttpRequests(authRequest ->
              authRequest
                .requestMatchers("/login/**", "/css/**", "/js/**", "/imagenes/**").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                    formLogin
                        .loginPage("/login") // Redirect unauthenticated users to /login
                        .defaultSuccessUrl("/clientes/", true)
                )
            .sessionManagement(sessionManager->
                sessionManager 
                  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .authenticationProvider(authProvider)
            .build();
            
            
    }
}