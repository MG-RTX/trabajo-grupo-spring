package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.DetallesPedidoRepository;
import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.dao.ProductoRepository;
import com.distribuida.bar_spring.model.DetallesPedido;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.Producto;
import com.distribuida.bar_spring.model.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.w3c.dom.ls.LSInput;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetalleServicioTestUnitaria {

    @Mock
    private DetallesPedidoRepository detallesPedidoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private DetallesPedido detallesPedido;
    private Pedido pedido;
    private Producto producto;
    private cliente cliente;

    @InjectMocks
    private DetallesPedidosServiceImpl detallesPedidoService;
    private PedidoServiceImpl pedidoService;
    private ProductoServiceImpl productoService;
    private clienteServiceImpl clienteService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        detallesPedido = new DetallesPedido(1,2,12.21,12.45,pedido,producto);
        pedido = new Pedido(1,new Date(),12.56,cliente);
        producto = new Producto(1,"Donas",12.34,12);
    }

    @Test
    public void testfindAll(){
        when(detallesPedidoRepository.findAll()).thenReturn(java.util.Arrays.asList(detallesPedido));
        List<DetallesPedido> detallesPedidos = detallesPedidoService.findAll();
        assertNotNull(detallesPedidos);
        assertEquals(1,detallesPedidos.size());
        verify(detallesPedidoRepository,times(1)).findAll();
    }

    @Test
    public void testfindOne(){
        when(detallesPedidoRepository.findById(1)).thenReturn(java.util.Optional.of(detallesPedido));
        DetallesPedido detallePedidoExistente = detallesPedidoService.findOne(1);
        assertNotNull(detallePedidoExistente);
        assertEquals(2,detallePedidoExistente.getCantidad());
        verify(detallesPedidoRepository,times(1)).findById(1);
    }

    @Test
    public void save(){
        when(detallesPedidoRepository.save(detallesPedido)).thenReturn(detallesPedido);
        DetallesPedido detallesPedido1 = detallesPedidoService.save(detallesPedido);
        assertNotNull(detallesPedido1);
        assertEquals(2,detallesPedido1.getCantidad());
        verify(detallesPedidoRepository,times(1)).save(detallesPedido);
    }

    @Test
    public void update(){
        DetallesPedido detallesPedidoActualizado = new DetallesPedido(1,1,12.23,34.34,pedido,producto);
        when(detallesPedidoRepository.findById(1)).thenReturn(Optional.of(detallesPedido));
        when(detallesPedidoRepository.save(any(DetallesPedido.class))).thenReturn(detallesPedidoActualizado);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        DetallesPedido detallesPedido1 = detallesPedidoService.update(1,1,1,detallesPedido);
        assertNotNull(detallesPedido1);
        assertEquals(1,detallesPedido1.getCantidad());
        assertEquals(12.23,detallesPedido1.getPrecioUnitario());
        verify(detallesPedidoRepository).save(any(DetallesPedido.class));
        verify(pedidoRepository).findById(1);
        verify(productoRepository).findById(1);
    }

    @Test
    public void delete(){
        when(detallesPedidoRepository.existsById(1)).thenReturn(true);
        detallesPedidoService.delete(1);
        verify(detallesPedidoRepository,times(1)).deleteById(1);
    }

}
