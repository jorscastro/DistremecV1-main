package com.distrimec.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distrimec.web.modelos.entidades.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Integer>{

}
