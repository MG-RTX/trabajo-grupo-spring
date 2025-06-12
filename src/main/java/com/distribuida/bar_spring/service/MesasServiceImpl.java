package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.MesasRepository;
import com.distribuida.bar_spring.model.Mesas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesasServiceImpl implements MesasService{

    @Autowired
    private MesasRepository mesasRepository;

    @Override
    public List<Mesas> findAll(){
        return mesasRepository.findAll();
    }

    @Override
    public Mesas findOne(int id){
        Optional<Mesas>mesas=mesasRepository.findById(id);
        return mesas.orElse(null);
    }

    @Override
    public Mesas save(Mesas mesa){
        return mesasRepository.save(mesa);
    }

    @Override
    public Mesas update(int id, Mesas mesa){
        Mesas mesaExistente=findOne(id);
        if (mesaExistente == null){
            return null;
        }

        mesaExistente.setIdMesa(mesa.getIdMesa());
        mesaExistente.setNumeroMesa(mesa.getNumeroMesa());
        mesaExistente.setCapacidad(mesa.getCapacidad());
        mesaExistente.setUbicacion(mesa.getUbicacion());
        return mesasRepository.save(mesaExistente);
    }

    @Override
    public void delete(int id){
        if (mesasRepository.existsById(id)){
            mesasRepository.deleteById(id);
        }
    }



}
