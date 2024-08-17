package com.distrimec.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distrimec.web.modelos.entidades.Cliente;


    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        // You can add custom query methods here if needed
    }

