package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Mesas;
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
public class MesasRepositorioTestIntegracion{

    @Autowired
    private MesasRepository mesasRepository;

    @Test
    public void findAll(){
        List<Mesas> mesas = mesasRepository.findAll();
        assertNotNull(mesas);
        assertTrue(mesas.size()>0);
        for (Mesas item:mesas){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Mesas> mesa = mesasRepository.findById(1);
        assertTrue(mesa.isPresent(),"La mesa con id 1 no existe");
        System.out.println(mesa.toString());
    }

    @Test
    public void update(){
        Optional<Mesas> mesaupdate = mesasRepository.findById(1);

        assertTrue(mesaupdate.isPresent(),"La mesa con id 1 no existe");
        mesaupdate.orElse(null).setNumeroMesa(1);
        mesaupdate.orElse(null).setCapacidad(10);
        mesaupdate.orElse(null).setUbicacion("Cerca de la plaza");

        Mesas mesaactualizada = mesasRepository.save(mesaupdate.orElse(null));
        assertNotNull(mesaactualizada.getIdMesa(),"El id de la mesa no se ha guardado correctamente");
        assertEquals(1,mesaactualizada.getNumeroMesa());
        assertEquals(10,mesaactualizada.getCapacidad());
        assertEquals("Cerca de la plaza",mesaactualizada.getUbicacion());
    }

    @Test
    public void delete(){
        if(mesasRepository.existsById(2)){
            mesasRepository.deleteById(2);
        }
        assertFalse(mesasRepository.existsById(2));
    }


}
