package com.distrimec.web.modelos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @Column(name = "idtipo", nullable = false)
    private Integer idTipo;

    @Column(name = "nomtipo", length = 45)
    private String nomTipo;

    @Column(name = "descripcion", length = 225)
    private String descripcion;

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomTipo() {
        return nomTipo;
    }

    public void setNomTipo(String nomTipo) {
        this.nomTipo = nomTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
