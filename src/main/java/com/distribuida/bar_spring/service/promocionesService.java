package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.promociones;

import java.util.List;

public interface promocionesService {

    public List<promociones> findAll();
    public promociones findOne(int id);
    public promociones save(promociones promociones);
    public promociones update(int id, promociones promociones);
    public void delete(int id);
}
