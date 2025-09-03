package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByFechaPedidoBetween(Date fechaInicio, Date fechaFin);

    List<Pedido> findByTotalGreaterThan(Double totalMinimo);

    List<Pedido> findByClienteIdCliente(int idCliente);  // Esto está correcto si la relación se llama "cliente"

    Optional<Pedido> findAllById(int idPedido);
}