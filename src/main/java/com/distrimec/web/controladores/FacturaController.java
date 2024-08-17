package com.distrimec.web.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distrimec.web.modelos.entidades.Producto;
import com.distrimec.web.modelos.entidades.Proveedor;
import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.modelos.mapper.MapperFactura;
import com.distrimec.web.modelos.entidades.Cliente;
import com.distrimec.web.modelos.entidades.Factura;
import com.distrimec.web.servicios.ProductoService;
import com.distrimec.web.servicios.ClienteService;
import com.distrimec.web.servicios.DetalleFacturaService;
import com.distrimec.web.servicios.FacturaService;
import com.distrimec.web.servicios.ProveedorServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorServicio proveedorService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;


    private static final Integer VENTAS = 1;
    private static final Integer COMPRAS = 2;

    @GetMapping("/venta")
    public String venta(Model model) {        
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);    
        return "facturas/facturaVenta";
    }

    @GetMapping("/compra")
    public String compra(Model model) {        
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("productos", productos);        
        return "facturas/facturaCompra";
    }

    @PostMapping("/guardarFactura")
    public ResponseEntity<?> guardarFactura(@RequestBody MapperFactura mapperFactura,Authentication authentication) {
        try{
            //obtener usuario
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = (Usuario) auth.getPrincipal();
            //gestionar guardado de factura
            Factura newFactura=null;
            if (mapperFactura.getCliente() != null) {
               newFactura= facturaService.crearFatura(mapperFactura,VENTAS,usuario);
            }else{
                newFactura= facturaService.crearFatura(mapperFactura,COMPRAS,usuario);
            }
            
            
            detalleFacturaService.crearDetalleFatura(mapperFactura,newFactura);
            
            return new ResponseEntity<>(new ApiResponse(true, "Factura recibida correctamente"), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false, "Error al recibir la factura"), HttpStatus.BAD_REQUEST);
        }
        
    }
    
}
