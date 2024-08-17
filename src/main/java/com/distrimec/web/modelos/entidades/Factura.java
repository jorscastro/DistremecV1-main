package com.distrimec.web.modelos.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura", nullable = false)
    private Integer idFactura;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "clienteid")
    private Integer clienteId;

    @Column(name = "usuariocod")
    private Integer usuarioCod;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteid", referencedColumnName = "idcliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo", referencedColumnName = "idtipo", insertable = false, updatable = false)
    private Tipo tipoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariocod", referencedColumnName = "codusuario", insertable = false, updatable = false)
    private Usuario usuario;

     @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleFactura> detalleFacturas;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(Integer usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipo getTipoEntity() {
        return tipoEntity;
    }

    public void setTipoEntity(Tipo tipoEntity) {
        this.tipoEntity = tipoEntity;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return detalleFacturas;
    }

    public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
        this.detalleFacturas = detalleFacturas;
    }
}