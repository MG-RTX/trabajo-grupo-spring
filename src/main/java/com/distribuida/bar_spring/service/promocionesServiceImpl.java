package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.promocionesRepository;
import com.distribuida.bar_spring.model.promociones;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class promocionesServiceImpl implements promocionesService{

    @Autowired
    private promocionesRepository promocionesRepository;


    @Override
    public List<promociones> findAll() {
        return promocionesRepository.findAll();
    }

    @Override
    public promociones findOne(int id) {
        Optional<promociones> promociones = promocionesRepository.findById(id);
        return promociones.orElse(null);
    }

    @Override
    public promociones save(promociones promociones) {
        return promocionesRepository.save(promociones);
    }

    @Override
    public promociones update(int id, promociones promocionesNuevo) {
        promociones promocionesExistente = findOne(id);

        if (promocionesExistente == null){
            return null;
        }
        promocionesExistente.setIdPromocion(promocionesNuevo.getIdPromocion());
        promocionesExistente.setNombrepromocion(promocionesNuevo.getNombrepromocion());
        promocionesExistente.setDescripcion(promocionesNuevo.getDescripcion());
        promocionesExistente.setDescuento(promocionesNuevo.getDescuento());
        promocionesExistente.setFechaInicio(promocionesNuevo.getFechaInicio());
        promocionesExistente.setFechaFin(promocionesNuevo.getFechaFin());
        promocionesExistente.setActivo(promocionesNuevo.getActivo());
        return promocionesRepository.save(promocionesExistente);

    }

    @Override
    public void delete(int id) {
        if (promocionesRepository.existsById(id)){
            promocionesRepository.deleteById(id);
        }

    }
}
