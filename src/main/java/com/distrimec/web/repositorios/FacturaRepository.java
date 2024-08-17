package com.distrimec.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distrimec.web.modelos.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura,Integer>{

}
