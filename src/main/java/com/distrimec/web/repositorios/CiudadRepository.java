package com.distrimec.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distrimec.web.modelos.entidades.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, String>{

}
