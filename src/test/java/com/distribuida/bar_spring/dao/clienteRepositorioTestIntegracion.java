package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.swing.plaf.PanelUI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class clienteRepositorioTestIntegracion {

    @Autowired
    private clienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<cliente> cliente = clienteRepository.findById(2);
        assertTrue(cliente.isPresent(),"El cliente con id 2 no existe");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        cliente cliente = new cliente(0,"Marco","Guacapiña","Software",new Date());
        clienteRepository.save(cliente);
        assertNotNull(cliente.getIdCliente(),"El id del cliente no se ha guardado correctamente");
        assertEquals("Marco",cliente.getNombre());
        assertEquals("Software",cliente.getCarrera());
    }

    @Test
    public void update(){
        Optional<cliente> clientes = clienteRepository.findById(2);

        assertTrue(clientes.isPresent(),"El cliente con id 1 no existe");
        clientes.orElse(null).setNombre("Firmino");
        clientes.orElse(null).setCarrera("Detecho");
        clientes.orElse(null).setCorreo("test@gmail.com");
        clientes.orElse(null).setFechaRegistro(new Date());

        cliente clienteactualizado = clienteRepository.save(clientes.orElse(null));
        assertNotNull(clienteactualizado.getIdCliente(),"El id del cliente no se ha guardado correctamente");
        assertEquals("Firmino",clienteactualizado.getNombre());
        assertEquals("Detecho", clienteactualizado.getCarrera());
    }

    @Test
    public void delete(){
    if(clienteRepository.existsById(3)){
        clienteRepository.deleteById(3);
    }
    assertFalse(clienteRepository.existsById(3));
    }




    }




