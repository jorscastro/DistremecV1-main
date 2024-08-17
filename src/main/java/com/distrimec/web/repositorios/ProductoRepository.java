package com.distrimec.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distrimec.web.modelos.entidades.Producto;



public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
