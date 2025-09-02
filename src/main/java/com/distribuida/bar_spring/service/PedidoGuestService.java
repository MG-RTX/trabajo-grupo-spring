package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.PedidoGuest;
import java.util.List;
import java.util.Optional;

public interface PedidoGuestService {
    PedidoGuest createOrGet(String token);
    Optional<PedidoGuest> getByToken(String token);
    PedidoGuest addItem(String token, int productoId, int cantidad);
    PedidoGuest updateItem(String token, int itemId, int cantidad);
    void removeItem(String token, int itemId);
    void clear(String token);
    PedidoGuest calcularTotales(PedidoGuest pedido);
}