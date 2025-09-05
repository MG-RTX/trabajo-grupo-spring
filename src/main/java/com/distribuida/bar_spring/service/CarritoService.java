package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Carrito;

public interface CarritoService {

    Carrito getOrCreatedByClienteId(int clienteId, String token);
    Carrito addItem(int clienteId, int productoId, int cantidad);
    Carrito updateItemCantidad(int clienteId, long carritoItemId, int nuevaCantidad);
    void removeItem(int clienteId, long carritoItemId);
    void clear(int clienteId);
    Carrito getByClienteId(int clienteId);

    Carrito getOrCreatedByToken(String token);
    Carrito addItem(String token, int productoId, int cantidad);
    Carrito updateItemCantidad(String token, long carritoItemId, int nuevaCantidad);
    void removeItem(String token, long carritoItemId);
    void clearByToken(String token);
    Carrito getByToken(String token);
}
