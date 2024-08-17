package com.distrimec.web.servicios;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distrimec.web.modelos.entidades.Factura;
import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.modelos.mapper.MapperFactura;
import com.distrimec.web.repositorios.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository iFacturaRepository;

    public Factura crearFatura(MapperFactura mapperFactura, Integer tipo,Usuario usuario){
            Factura factura = new Factura();
            factura.setTotal(BigDecimal.valueOf(mapperFactura.getTotal()));
            factura.setFecha(LocalDateTime.now());
            factura.setClienteId(mapperFactura.getCliente());
            factura.setTipo(tipo);
            System.out.println("cedula: "+usuario.getCedula());
            factura.setUsuarioCod(usuario.getCodUsuario());
            factura.setUsuario(usuario);
        return iFacturaRepository.save(factura);
    }

}
