package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.cliente;

import java.util.List;

public interface clienteService {

    public List<cliente> findAll();
    public cliente findOne(int id);
    public cliente save(cliente cliente);
    public cliente update(int id, cliente cliente);
    public void delete(int id);
}
