package com.distrimec.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.distrimec.web.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository userRepository;
private final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
{
    logger.info("Creating AuthenticationManager bean");
    return config.getAuthenticationManager();
}

@Bean
public AuthenticationProvider authenticationProvider()
{
    logger.info("Creating AuthenticationProvider bean");
    DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
}

@Bean
public PasswordEncoder passwordEncoder() {
    logger.info("Creating PasswordEncoder bean");
    return new BCryptPasswordEncoder();
}

@Bean
public UserDetailsService userDetailService() {
    
    UserDetailsService userD =  cedula -> {
        logger.info("Finding user by cedula: {}", cedula);
        return userRepository.findByCedula(Integer.parseInt(cedula))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    };

    logger.info("Creating UserDetailsService bean", userD.toString());
    return userD;
}

}
