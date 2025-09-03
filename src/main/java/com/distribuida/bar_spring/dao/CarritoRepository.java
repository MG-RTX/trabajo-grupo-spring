package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Carrito;
import com.distribuida.bar_spring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito,Integer> {
    Optional<Carrito> findByCliente(Cliente cliente);
    Optional<Carrito> findByToken(String token);
}
