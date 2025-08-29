package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.DetallesPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido, Integer> {

    List<DetallesPedido> findByPedidoIdPedido(int idPedido);  // Debe ser pedido.id (asumiendo que la relación se llama "pedido")

    List<DetallesPedido> findByProductoIdProducto(int idProducto);
    // Debe ser producto.id

    // Este método parece incorrecto ya que DetallesPedido no tiene campo "cantidad"
    // List<DetallesPedido> findByCantidadGreaterThan(int cantidadMinima);

    // En su lugar, podrías tener métodos como:
    List<DetallesPedido> findBySubtotalGreaterThan(Double subtotalMinimo);
}