package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.ClienteRepository;
import com.distribuida.bar_spring.model.Cliente;  // Cambiado a mayúscula
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll(){  // Cambiado a Cliente
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findOne(int id){  // Cambiado a Cliente
        Optional<Cliente> cliente = clienteRepository.findById(id);  // Cambiado a Cliente
        return cliente.orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente){  // Cambiado a Cliente
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(int id, Cliente clientenuevo){  // Cambiado a Cliente
        Cliente clienteexistente = findOne(id);  // Cambiado a Cliente

        if(clienteexistente == null){
            return null;
        }

        clienteexistente.setNombre(clientenuevo.getNombre());
        clienteexistente.setApellido(clientenuevo.getApellido());
        clienteexistente.setCorreo(clientenuevo.getCorreo());
        clienteexistente.setCedula(clientenuevo.getCedula());
        return clienteRepository.save(clienteexistente);
    }

    @Override
    public void delete(int id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }
    }
}