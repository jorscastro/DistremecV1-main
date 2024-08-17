package com.distrimec.web.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.distrimec.web.modelos.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar consultas personalizadas si es necesario
    Optional<Usuario> findByCedula(Integer cedula);
}
