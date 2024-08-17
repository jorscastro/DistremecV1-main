package com.distrimec.web.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.distrimec.web.modelos.entidades.Producto;
import com.distrimec.web.repositorios.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository iProductoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return iProductoRepository.findAll();
    }

    public Producto obtenerProductoxId(Integer id){
        return iProductoRepository.findById(id).orElseThrow();
    }

     public Producto actualizarProducto(Integer codProducto, Producto productoActualizado) {
        productoActualizado.setCodProducto(codProducto);
        // Actualizar otros campos según sea necesario
        return iProductoRepository.save(productoActualizado);
    }
    public void  eliminarProducto(Integer codProducto){
        iProductoRepository.deleteById(codProducto);

    }
    public Producto crearProducto(Producto producto){
        return iProductoRepository.save(producto);


    }
}
