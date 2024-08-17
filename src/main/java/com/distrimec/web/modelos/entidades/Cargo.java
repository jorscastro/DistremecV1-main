package com.distrimec.web.modelos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcargo")
    private int idCargo;

    @Column(name = "nombre", length = 85)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    // Constructors, Getters, and Setters
    public Cargo() {
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
