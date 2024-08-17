package com.distrimec.web.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distrimec.web.modelos.entidades.DetalleFactura;
import com.distrimec.web.modelos.entidades.Factura;
import com.distrimec.web.modelos.entidades.Producto;
import com.distrimec.web.modelos.mapper.MapperFactura;
import com.distrimec.web.modelos.mapper.MapperProducto;
import com.distrimec.web.repositorios.DetalleFacturaRepository;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository iDetalleFacturaRepository;

    @Autowired
    private ProductoService productoService;

    public List<DetalleFactura> crearDetalleFatura(MapperFactura mapperFactura, Factura newFactura){
        List<DetalleFactura> detalles = new ArrayList<>();
        for (MapperProducto  mapperProducto: mapperFactura.getProductos()) {
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setCantidad(mapperProducto.getCantidad());
                detalleFactura.setSubtotal(mapperProducto.getPrecio()*mapperProducto.getCantidad());
                Producto producto = productoService.obtenerProductoxId(mapperProducto.getId());
                detalleFactura.setProducto(producto);
                detalleFactura.setProductoCod(producto.getCodProducto());
                detalleFactura.setIva(0.0);
                detalleFactura.setValor(mapperProducto.getPrecio());
                detalleFactura.setFacturaId(newFactura.getIdFactura());
                detalles.add(iDetalleFacturaRepository.save(detalleFactura));

            }
        return detalles;
    }

}
