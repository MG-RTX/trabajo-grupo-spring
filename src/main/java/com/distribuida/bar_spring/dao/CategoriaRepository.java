package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByNombreCategoria(String nombreCategoria);

    List<Categoria> findByNombreCategoriaContaining(String nombre);
}
