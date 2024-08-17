package com.distrimec.web.servicios;

import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;

 
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
}

