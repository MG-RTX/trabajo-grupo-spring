package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.promociones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface promocionesRepository extends JpaRepository <promociones,Integer> {
    promociones findByNombre(String nombre);
}
