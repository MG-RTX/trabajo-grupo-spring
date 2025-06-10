package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}
