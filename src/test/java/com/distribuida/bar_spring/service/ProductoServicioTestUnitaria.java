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





}
