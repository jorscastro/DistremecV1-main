package com.distrimec.web.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.servicios.CiudadService;
import com.distrimec.web.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadService ciudadService;

    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/")
    public String listarUsuarios(Model model, Authentication authentication, HttpServletRequest request) {
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("Usuario autenticado: ".concat(auth.getName()));
        }

        return "usuarios/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        model.addAttribute("cargos", usuarioServicio.listarCargos());
        return "usuarios/formulario";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        usuarioServicio.crearUsuario(usuario);
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "redirect:/usuarios/";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        model.addAttribute("cargos", usuarioServicio.listarCargos());
        return "usuarios/formulario";
    }

    @PostMapping("/{id}/editar")
    public String editarUsuario(@PathVariable("id") Integer id, @ModelAttribute("usuario") Usuario usuario, Model model) {
        usuario.setCodUsuario(id);
        usuarioServicio.editarUsuario(usuario);
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "redirect:/usuarios/";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarUsuario(@PathVariable("id") Integer id, Model model) {
        usuarioServicio.eliminarUsuario(id);
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "redirect:/usuarios/";
    }
}