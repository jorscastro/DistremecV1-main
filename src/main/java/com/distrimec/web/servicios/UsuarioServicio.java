package com.distrimec.web.servicios;

import com.distrimec.web.modelos.entidades.Cargo;
import com.distrimec.web.modelos.entidades.Role;
import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.repositorios.CargoRepository;
import com.distrimec.web.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

 
    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuario(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            // Handle the case where the user isn't found. This is just a simple example.
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public Usuario editarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar el usuario
        String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setContraseña(encodedPassword);
        usuario.setRole(Role.USER); 
        return usuarioRepository.save(usuario);
    }
}

