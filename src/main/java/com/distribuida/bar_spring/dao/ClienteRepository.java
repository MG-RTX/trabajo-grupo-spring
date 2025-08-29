package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Cliente;  // Cambiado a mayúscula
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {  // Cambiado a mayúscula

    Cliente findByNombre(String nombre);  // Cambiado a Cliente

    List<Cliente> findByCorreo(String correo);  // Cambiado a Cliente
}