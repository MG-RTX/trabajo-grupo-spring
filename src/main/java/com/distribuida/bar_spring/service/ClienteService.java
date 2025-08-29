package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Cliente;  // Cambiado a mayúscula

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll();  // Cambiado a Cliente
    public Cliente findOne(int id);  // Cambiado a Cliente
    public Cliente save(Cliente cliente);  // Cambiado a Cliente
    public Cliente update(int id, Cliente cliente);  // Cambiado a Cliente
    public void delete(int id);
}