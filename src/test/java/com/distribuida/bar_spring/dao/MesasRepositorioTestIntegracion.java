package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Mesas;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

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
    public void save(){
        Mesas mesas = new Mesas(1,1,2,"Pifo");
        mesasRepository.save(mesas);
        assertNotNull(mesas.getIdMesa(),"El id de la mesa no existe");
        assertEquals(1,mesas.getNumeroMesa());
        assertEquals(2,mesas.getCapacidad());
        assertEquals("Pifo",mesas.getUbicacion());
    }

}
