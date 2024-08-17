package com.distrimec.web.modelos.mapper;

import java.util.List;


public class MapperFactura {

    private Integer cliente;
	private Integer proveedor;
    private List<MapperProducto> productos;
    private double total;
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getProveedor() {
		return proveedor;
	}
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}
	public List<MapperProducto> getProductos() {
		return productos;
	}
	public void setProductos(List<MapperProducto> productos) {
		this.productos = productos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
    
    

}
