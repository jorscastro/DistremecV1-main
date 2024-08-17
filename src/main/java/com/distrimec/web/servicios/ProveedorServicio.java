package com.distrimec.web.servicios;

import com.distrimec.web.modelos.entidades.Proveedor;
import com.distrimec.web.repositorios.ProveedorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProveedorServicio {

    private final ProveedorRepository proveedorRepositorio;

   
    public ProveedorServicio(ProveedorRepository proveedorRepositorio) {
        this.proveedorRepositorio = proveedorRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    @Transactional
    public void crearProveedor(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }

    @Transactional(readOnly = true)
    public Proveedor obtenerProveedor(Integer id) {
        return proveedorRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void actualizarProveedor(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }

    @Transactional
    public void eliminarProveedor(Integer id) {
        proveedorRepositorio.deleteById(id);
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepositorio.findAll();
    }
}
