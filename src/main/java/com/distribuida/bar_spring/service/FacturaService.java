package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Factura;

import java.util.List;

public interface FacturaService {
    List<Factura> findAll();
    Factura findOne(int id);
    Factura findByPedido(int idPedido);
    Factura generarFactura(int pedidoId);
    void delete(int id);
}