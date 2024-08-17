package com.distrimec.web.controladores;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.distrimec.web.modelos.entidades.Cliente;
import com.distrimec.web.servicios.CiudadService;
import com.distrimec.web.servicios.ClienteService;

import jakarta.servlet.http.HttpServletRequest;




@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CiudadService ciudadService; 

    Logger logger = LoggerFactory.getLogger(ClienteController.class);

    

    @GetMapping("/")
    public String listarClientes(Model model,Authentication authentication,
			HttpServletRequest request) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        

		if(auth != null) {
			logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): Usuario autenticado: ".concat(auth.getName()));

            if(hasRole("ADMIN")) {
                logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
            } else {
                logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
            }
		}

        
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        return "clientes/formulario";
    }

    @PostMapping("/nuevo")
    public String crearCliente(@ModelAttribute Cliente cliente,Model model) {
        clienteService.crearCliente(cliente);
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "redirect:/clientes/";
    }

    @GetMapping("/{id}")
    public String mostrarDetallesCliente(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.obtenerClientePorId(id));
        return "clientes/detalles";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCliente(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.obtenerClientePorId(id));
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        return "clientes/formulario";
    }

    @PostMapping("/{idCliente}/editar")
    public String actualizarCliente(@PathVariable Integer idCliente, @ModelAttribute Cliente cliente, Model model) {
        Cliente clienteActual = clienteService.obtenerClientePorId(idCliente);
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setTelefono(cliente.getTelefono());
        clienteActual.setCiudad(cliente.getCiudad());
        clienteActual.setCorreo(cliente.getCorreo());
        clienteService.actualizarCliente(idCliente,clienteActual);
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "redirect:/clientes/";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        clienteService.eliminarCliente(cliente.getIdCliente());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "redirect:/clientes/";
    }

    private boolean hasRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		//return authorities.contains(new SimpleGrantedAuthority(role));
		logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authorities.toString())));
		 for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authority.getAuthority())));
				return true;
			}
		}
		
		return false;
		
		
	}
}
