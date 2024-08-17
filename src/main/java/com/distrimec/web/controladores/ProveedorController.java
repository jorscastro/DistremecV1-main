package com.distrimec.web.controladores;

import com.distrimec.web.modelos.entidades.Proveedor;
import com.distrimec.web.servicios.ProveedorServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorServicio proveedorServicio;

   
    public ProveedorController(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    @GetMapping("/")
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorServicio.listarProveedores());
        return "proveedores/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    @PostMapping("/crear")
    public String crearProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        proveedorServicio.crearProveedor(proveedor);
        return "redirect:/proveedores/";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Proveedor proveedor = proveedorServicio.obtenerProveedor(id);
        model.addAttribute("proveedor", proveedor);
        return "proveedores/formulario";
    }

    @PostMapping("/{codProveedor}/editar")
    public String editarProveedor(@PathVariable("codProveedor") Integer id, @ModelAttribute("proveedor") Proveedor proveedor, Model model) {
        Proveedor proveedorActual = proveedorServicio.obtenerProveedor(id);
        proveedorActual.setEmpresa(proveedor.getEmpresa());
        proveedorActual.setNit(proveedor.getNit());
        proveedorActual.setDireccion(proveedor.getDireccion());
        proveedorActual.setTelefono(proveedor.getTelefono());
        proveedorActual.setCorreo(proveedor.getCorreo());
        proveedorServicio.actualizarProveedor(proveedorActual);
        model.addAttribute("proveedores", proveedorServicio.listarProveedores());
        return "redirect:/proveedores/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Integer id) {
        proveedorServicio.eliminarProveedor(id);
        return "redirect:/proveedores/";
    }
}
