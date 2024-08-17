package com.distrimec.web.modelos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @Column(name = "idciudad", nullable = false, length = 4)
    private String idCiudad;

    @Column(name = "descriciudad", length = 75)
    private String descriCiudad;

    // Getters and Setters

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDescriCiudad() {
        return descriCiudad;
    }

    public void setDescriCiudad(String descriCiudad) {
        this.descriCiudad = descriCiudad;
    }
}
