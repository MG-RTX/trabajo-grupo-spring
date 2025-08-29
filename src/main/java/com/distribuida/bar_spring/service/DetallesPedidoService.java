package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.DetallesPedido;

import java.util.List;

public interface DetallesPedidoService {
    public List<DetallesPedido> findAll();
    public DetallesPedido findOne(int id);
    public DetallesPedido save(DetallesPedido detallesPedido);
    public DetallesPedido update(int id, DetallesPedido detallesPedido);
    public void delete(int id);
}