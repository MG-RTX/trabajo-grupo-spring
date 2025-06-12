package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Mesas;

import java.util.List;

public interface MesasService {

    public List<Mesas> findAll();
    public Mesas findOne(int id);
    public Mesas save(Mesas mesa);
    public Mesas update(int id, Mesas mesa);
    public void delete(int id);
}
