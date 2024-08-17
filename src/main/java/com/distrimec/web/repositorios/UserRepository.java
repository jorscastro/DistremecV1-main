package com.distrimec.web.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distrimec.web.modelos.entidades.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); 
}
