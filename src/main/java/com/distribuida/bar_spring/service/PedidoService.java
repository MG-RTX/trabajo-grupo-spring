package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Pedido;

import java.util.List;

public interface PedidoService {
    public List<Pedido> findAll();
    public Pedido findOne(int id);
    public Pedido save(Pedido pedido);
    public Pedido update(int id, Pedido pedido);
    public void delete(int id);
}