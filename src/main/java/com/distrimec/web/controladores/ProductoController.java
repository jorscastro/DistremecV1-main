package com.distrimec.web.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distrimec.web.modelos.entidades.Cliente;
import com.distrimec.web.modelos.entidades.Producto;
import com.distrimec.web.servicios.ProductoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

     @GetMapping("/")
    public String listarProductos(Model model,
			HttpServletRequest request) {
        model.addAttribute("productos",productoService.obtenerTodosLosProductos()); 
        return "productos/listar";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formulario";
    }
    @PostMapping("/nuevo")
    public String crearProducto(@ModelAttribute Producto producto,Model model) {
        productoService.crearProducto(producto);
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "redirect:/productos/";
    }

     @GetMapping("/{codProducto}/editar")
    public String mostrarFormularioEditarproducto(@PathVariable Integer codProducto, Model model) {
        model.addAttribute("producto", productoService.obtenerProductoxId(codProducto));
        return "productos/formulario";
    }

    @PostMapping("/{codProducto}/editar")
    public String actualizarProducto(@PathVariable Integer codProducto, @ModelAttribute Producto producto, Model model) {
        Producto productoActual = productoService.obtenerProductoxId(codProducto);
        productoActual.setCodProducto(producto.getCodProducto());
        productoActual.setNombreProducto(producto.getNombreProducto());
        productoActual.setLote(producto.getLote());
        productoActual.setPrecio(producto.getPrecio());
        productoActual.setFechaVencimiento(producto.getFechaVencimiento());
        productoActual.setProveedorCod(producto.getProveedorCod());
        productoActual.setStock(producto.getStock());
        productoService.actualizarProducto(codProducto,productoActual);
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "redirect:/productos/";
    }

    
    @GetMapping("/{codProducto}/eliminar")
    public String eliminarProducto(@PathVariable Integer codProducto, Model model) {
        Producto  producto  = productoService.obtenerProductoxId(codProducto);
        productoService.eliminarProducto(producto.getCodProducto());
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "productos/listar";
    }

    
		
	
}
