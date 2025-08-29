package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    List<Producto> findByPrecioLessThanEqual(Double precioMaximo);

    List<Producto> findByStockGreaterThan(int stockMinimo);

    List<Producto> findByNombreProductoContaining(String nombre);
}