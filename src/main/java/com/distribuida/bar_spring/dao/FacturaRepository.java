package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    Factura findByPedidoIdPedido(int idPedido);
}