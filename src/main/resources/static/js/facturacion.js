$(document).ready(function() {
    $('#agregarProducto').click(function() {
        agregarProductoATabla();
    });

    $('#guardarFactura').click(function() {
        guardarFactura();
    });
});

let productosEnTabla = [];

function agregarProductoATabla() {
    const productoSelect = $('#productoSelect');
    const productoSeleccionado = productoSelect.find('option:selected');
    const producto = {
        id: productoSeleccionado.val(),
        nombre: productoSeleccionado.text(),
        precio: parseFloat(productoSeleccionado.data('precio')),
        cantidad: $('#productoSelect').val(), // Por defecto agregamos 1, se puede modificar para permitir seleccionar cantidad
        subtotal: function() { return this.precio * this.cantidad; }
    };

    productosEnTabla.push(producto);
    actualizarTablaProductos();
}

function actualizarTablaProductos() {
    const tablaProductos = $('#tablaProductos');
    tablaProductos.empty(); // Limpiar tabla

    $.each(productosEnTabla, function(index, producto) {
        const fila = $('<tr></tr>');
        fila.append($('<td></td>').text(producto.nombre));
        fila.append($('<td></td>').text(producto.cantidad));
        fila.append($('<td></td>').text(producto.precio.toFixed(2)));
        fila.append($('<td></td>').text(producto.subtotal().toFixed(2)));
        const accionCelda = $('<td></td>');
        const botonEliminar = $('<button>Eliminar</button>').addClass('btn btn-danger').click(function() {
            eliminarProductoDeTabla(index);
        });
        accionCelda.append(botonEliminar);
        fila.append(accionCelda);

        tablaProductos.append(fila);
    });

    calcularTotal();
}

function eliminarProductoDeTabla(index) {
    productosEnTabla.splice(index, 1);
    actualizarTablaProductos();
}

function calcularTotal() {
    const total = productosEnTabla.reduce((acc, producto) => acc + producto.subtotal(), 0);
    $('#totalFactura').text(total.toFixed(2));
    return total;
}

function obtenerCliente(){
    const clienteSelect = $('#clienteSelect');
    const clienteSeleccionado =  clienteSelect.find('option:selected');
    return clienteSeleccionado.val();
}

function obtenerProveedor(){
    const proveedorSelect = $('#proveedorSelect');
    const proveedorSelececionado = proveedorSelect.find('option:selected');
    return proveedorSelececionado.val();
}

function guardarFactura() {
    // Implementar lógica para enviar los productosEnTabla y el total
    const data = {
        cliente: obtenerCliente(),
        proveedor: obtenerProveedor(),
        productos: productosEnTabla,
        total: calcularTotal()
    };

    console.log(data);

    fetch('/factura/guardarFactura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(response => {
        console.log('Factura guardada:', response);
    })
    .catch(error => {
        console.error('Error al guardar la factura:', error);
    });
    // a un servidor o API para guardar la factura. Esto podría hacerse con $.ajax o $.post de jQuery.

    console.log('Guardar factura:', productosEnTabla);
}