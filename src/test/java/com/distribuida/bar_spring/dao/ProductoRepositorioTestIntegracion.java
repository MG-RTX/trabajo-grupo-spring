package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Producto;
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
public class ProductoRepositorioTestIntegracion {
    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void findAll(){
        List<Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertTrue(productos.size()>0);
        for (Producto item:productos){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Producto> productoOptional = productoRepository.findById(1);
        assertTrue(productoOptional.isPresent(), "El producto debería existir");
        System.out.println(productoOptional.toString());
    }

    @Test
    public void save(){
        Producto productos = new Producto(1,"Pizza",1.25,5);
        productoRepository.save(productos);
        assertNotNull(productos.getIdProducto(),"El id del producto no se ha guardado correctamente");
        assertEquals("Pizza",productos.getNombreProducto());
        assertEquals(1.25,productos.getPrecio());
        assertEquals(5,productos.getStock());
    }

    @Test
    public void update(){
        Optional<Producto> productoupdate = productoRepository.findById(1);

        assertTrue(productoupdate.isPresent(),"El producto con id 1 no existe");
        productoupdate.orElse(null).setNombreProducto("Pizza");
        productoupdate.orElse(null).setPrecio(1.25);
        productoupdate.orElse(null).setStock(5);

        Producto productoactualizado = productoRepository.save(productoupdate.orElse(null));
        assertNotNull(productoactualizado.getIdProducto(),"El id del producto no se ha guardado correctamente");
        assertEquals("Pizza",productoactualizado.getNombreProducto());
        assertEquals(1.25,productoactualizado.getPrecio());
        assertEquals(5,productoactualizado.getStock());
    }

    @Test
    public void delete(){
        if(productoRepository.existsById(2)){
            productoRepository.deleteById(2);
        }
        assertFalse(productoRepository.existsById(2));
    }

}