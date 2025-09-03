package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Carrito;
import com.distribuida.bar_spring.model.CarritoItem;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoItemRepository  extends JpaRepository<CarritoItem, Long> {
    Optional<CarritoItem> findbyCarritoAndPedido(Carrito carrito, Pedido pedido);
    List<CarritoItem> findByCarrito(Carrito carrito);
}
