package com.distrimec.web.modelos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medicamentos")
public class Producto {

    @Id
    @Column(name = "codproducto", nullable = false)
    private Integer codProducto;

    @Column(name = "producto", length = 20)
    private String nombreProducto;

    @Column(name = "lote")
    private Integer lote;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "fechavencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "stock", length = 45)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedorcod", referencedColumnName = "codproveedor")
    private Proveedor proveedor;

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
