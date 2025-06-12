package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesasRepository extends JpaRepository<Mesas,Integer> {
    Mesas findByNumeroMesa(int numeroMesa);

}
