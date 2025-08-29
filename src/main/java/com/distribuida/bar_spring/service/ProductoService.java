package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> findAll();
    public Producto findOne(int id);
    public Producto save(Producto producto);
    public Producto update(int id, Producto producto);
    public void delete(int id);
}