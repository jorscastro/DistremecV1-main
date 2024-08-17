package com.distrimec.web.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.distrimec.web.modelos.entidades.Cliente;
import com.distrimec.web.repositorios.ClienteRepository;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setCiudad(clienteActualizado.getCiudad());
        // Actualizar otros campos según sea necesario
        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(Integer id) {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteRepository.delete(clienteExistente);
    }

}
