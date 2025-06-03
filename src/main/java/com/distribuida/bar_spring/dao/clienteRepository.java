package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clienteRepository extends JpaRepository<cliente, Integer> {

    cliente findByNombre(String nombre);
}
