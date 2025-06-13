package com.distribuida.bar_spring.service;


import com.distribuida.bar_spring.dao.ProductoRepository;
import com.distribuida.bar_spring.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServicioTestUnitaria {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    public void setUp(){
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Pizza");
        producto.setPrecio(1.25);
        producto.setStock(5);
    }

    @Test
    public void testfindAll(){
        when(productoRepository.findAll()).thenReturn(java.util.Arrays.asList(producto));
        List<Producto> productos = productoService.findAll();
        assertNotNull(productos);
        assertEquals(1,productos.size());
        verify(productoRepository,times(1)).findAll();
    }

    @Test
    public void testfindOneExistente(){
        when(productoRepository.findById(1)).thenReturn(java.util.Optional.of(producto));
        Producto productoExistente = productoService.findOne(1);
        assertNotNull(productoExistente);
        assertEquals("Pizza",productoExistente.getNombreProducto());
    }

    @Test
    public void testfindOneNoExistente(){
        when(productoRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Producto producto = productoService.findOne(1);
        assertEquals(null,producto);
    }

    @Test
    public void testsave(){
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto productoExistente = productoService.save(producto);
        assertNotNull(productoExistente.getIdProducto());
        assertEquals("Pizza",productoExistente.getNombreProducto());
    }

    @Test
    public void testupdateExistente(){
        Producto productoActualizado = new Producto();
        productoActualizado.setIdProducto(1);
        productoActualizado.setNombreProducto("Pizza");
        productoActualizado.setPrecio(1.25);
        productoActualizado.setStock(5);
        when(productoRepository.findById(1)).thenReturn(java.util.Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(productoActualizado);
        Producto productoExistente = productoService.update(1,producto);
        assertNotNull(productoExistente);
        assertEquals("Pizza",productoExistente.getNombreProducto());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testupdateNoExistente(){
        Producto productoNuevo = new Producto();
        productoNuevo.setIdProducto(1);
        productoNuevo.setNombreProducto("Pizza");
        productoNuevo.setPrecio(1.25);
        productoNuevo.setStock(5);
        when(productoRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Producto productoExistente = productoService.update(1,productoNuevo);
        assertEquals(null,productoExistente);
    }

    @Test
    public void testdeleteExistente(){
        when(productoRepository.existsById(1)).thenReturn(true);
        productoService.delete(1);
        verify(productoRepository,times(1)).deleteById(1);
    }

    @Test
    public void testdeleteNoExistente(){
        when(productoRepository.existsById(1)).thenReturn(false);
        productoService.delete(1);
        verify(productoRepository,times(0)).deleteById(1);
    }
}
