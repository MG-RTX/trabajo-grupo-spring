package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.promociones;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class promocionesRepositorioTestIntegracion {

    @Autowired
    private promocionesRepository promocionesRepository;

    @Test
    public void findAll(){
        List<promociones> promociones = promocionesRepository.findAll();
        assertNotNull(promociones);
        assertTrue(promociones.size()>0);
        for (promociones item:promociones){
            System.out.println(item.toString());
        }
    }
    @Test
    public void findOne(){
        Optional<promociones> promociones = promocionesRepository.findById(1);
        assertTrue(promociones.isPresent(),"La promocion con id 1 no existe");
        System.out.println(promociones.toString());
    }
    @Test
    public void save(){
        promociones promociones= new promociones(1,"Navidad","Descuentos por Niñito Jesus",12.34,"Lunes","Martes","Inhabilitado");
        promocionesRepository.save(promociones);
        assertNotNull(promociones.getIdPromocion(),"La promocion con el ID no se guardo");
        assertEquals("Navidad", promociones.getNombrepromocion());
        assertEquals("Lunes", promociones.getFechaInicio());
    }
    @Test
    public void update(){
        Optional<promociones> promociones = promocionesRepository.findById(1);
        assertTrue(promociones.isPresent(),"La promocion con ID 1 no existe");
        promociones.orElse(null).setNombrepromocion("Navidad");
        promociones.orElse(null).setDescripcion("Solo por noche buena");
        promociones.orElse(null).setDescuento(24.23);
        promociones.orElse(null).setFechaInicio("Miercoles 24");
        promociones.orElse(null).setFechaFin("Jueves 25");
        promociones.orElse(null).setActivo("Activo");

        promociones promocionesActualizado = promocionesRepository.save(promociones.orElse(null));
        assertNotNull(promocionesActualizado.getIdPromocion(),"La promocion con el ID no se guardo");
        assertEquals("Navidad", promocionesActualizado.getNombrepromocion());
        assertEquals("Solo por noche buena", promocionesActualizado.getDescripcion());
        assertEquals(24.23, promocionesActualizado.getDescuento());
        assertEquals("Miercoles 24", promocionesActualizado.getFechaInicio());
        assertEquals("Jueves 25", promocionesActualizado.getFechaFin());
        assertEquals("Activo", promocionesActualizado.getActivo());
    }
    @Test
    public void delete(){
        if(promocionesRepository.existsById(2)){
            promocionesRepository.deleteById(2);
        }
        assertFalse(promocionesRepository.existsById(2));
    }

}
