package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedido,Integer>{
}
