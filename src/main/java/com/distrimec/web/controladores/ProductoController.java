package com.distrimec.web.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distrimec.web.modelos.entidades.Producto;
import com.distrimec.web.modelos.entidades.Proveedor;
import com.distrimec.web.servicios.ProductoService;
import com.distrimec.web.servicios.ProveedorServicio;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

        @Autowired
    private ProveedorServicio proveedorService;

     @GetMapping("/")
    public String listarProductos(Model model,
			HttpServletRequest request) {
        model.addAttribute("productos",productoService.obtenerTodosLosProductos()); 
        return "productos/listar";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        return "productos/formulario";
    }
    @PostMapping("/nuevo")
    public String crearProducto(@ModelAttribute("producto") Producto producto,Model model) {
        productoService.crearProducto(producto);
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "redirect:/productos/";
    }

     @GetMapping("/{codProducto}/editar")
    public String mostrarFormularioEditarproducto(@PathVariable Integer codProducto, Model model) {
        model.addAttribute("producto", productoService.obtenerProductoxId(codProducto));
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        return "productos/formulario";
    }

    @PostMapping("/{codProducto}/editar")
    public String actualizarProducto(@PathVariable Integer codProducto, @ModelAttribute Producto producto, Model model) {
        producto.setCodProducto(codProducto);
        productoService.actualizarProducto(codProducto,producto);
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
