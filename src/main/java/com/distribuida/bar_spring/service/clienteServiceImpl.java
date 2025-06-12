package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.clienteRepository;
import com.distribuida.bar_spring.model.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clienteServiceImpl implements clienteService{

    @Autowired
    private clienteRepository clienteRepository;

    @Override
    public List<cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Override
    public cliente findOne(int id){
        Optional<cliente>cliente=clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public cliente save(cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    public cliente update(int id, cliente clientenuevo){
        cliente clienteexistente=findOne(id);

        if(clienteexistente == null){
            return null;
        }

        clienteexistente.setIdCliente(clientenuevo.getIdCliente());
        clienteexistente.setCarrera(clientenuevo.getCarrera());
        clienteexistente.setCorreo(clientenuevo.getCorreo());
        clienteexistente.setNombre(clientenuevo.getNombre());
        clienteexistente.setFechaRegistro(clientenuevo.getFechaRegistro());
        return clienteRepository.save(clienteexistente);
    }

    @Override
    public void delete(int id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }
    }


}
