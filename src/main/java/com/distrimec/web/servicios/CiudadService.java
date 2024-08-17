package com.distrimec.web.servicios;

import com.distrimec.web.modelos.entidades.Ciudad;
import java.util.List;
import com.distrimec.web.repositorios.CiudadRepository;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;


    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadRepository.findAll();
    }
}
