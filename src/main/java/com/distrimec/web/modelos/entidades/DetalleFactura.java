package com.distrimec.web.modelos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factuprovemedi")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factuprovemedi", nullable = false)
    private Integer factuProveMediId;

    @Column(name = "productocod")
    private Integer productoCod;

    @Column(name = "facturaid")
    private Integer facturaId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productocod", referencedColumnName = "codproducto", insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facturaid", referencedColumnName = "idfactura", insertable = false, updatable = false)
    private Factura factura;

    public Integer getFactuProveMediId() {
        return factuProveMediId;
    }

    public void setFactuProveMediId(Integer factuProveMediId) {
        this.factuProveMediId = factuProveMediId;
    }

    public Integer getProductoCod() {
        return productoCod;
    }

    public void setProductoCod(Integer productoCod) {
        this.productoCod = productoCod;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double d) {
        this.subtotal = d;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double d) {
        this.valor = d;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}