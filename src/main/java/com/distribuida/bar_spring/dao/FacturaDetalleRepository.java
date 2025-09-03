package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Integer> {
}
