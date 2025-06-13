package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.dao.clienteRepository;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.w3c.dom.ls.LSInput;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidosServicioTestUnitaria {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private clienteRepository clienteRepository;

    @InjectMocks
    private Pedido pedido;
    private cliente cliente;

    @InjectMocks
    private PedidoServiceImpl pedidoService;
    private clienteServiceImpl clienteService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        pedido = new Pedido(1,new Date(),12.56,cliente);
        cliente = new cliente(1,"Alexandra","giomara@gmail.com","Enfermeria",new Date());
    }

    @Test
    public void testFindAll(){
        when(pedidoRepository.findAll()).thenReturn(java.util.Arrays.asList(pedido));
        List<Pedido> pedidos = pedidoService.findAll();
        assertNotNull(pedidos);
        assertEquals(1,pedidos.size());
        verify(pedidoRepository,times(1)).findAll();
    }

     @Test
    public void testFindOne(){
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        Pedido pedido1 = pedidoService.findOne(1);
        assertNotNull(pedido1);
        assertEquals(1,pedido1.getIdPedido());
        verify(pedidoRepository,times(1)).findById(1);
    }

    @Test
    public void testSave(){
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Pedido pedido1 = pedidoService.save(pedido);
        assertNotNull(pedido1);
        assertEquals(1,pedido1.getIdPedido());
        verify(pedidoRepository,times(1)).save(pedido);
    }

    @Test
    public void testUpdate(){
        Pedido pedidoActualizado = new Pedido(1,new Date(),12.56,cliente);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoActualizado);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        Pedido pedido1 = pedidoService.update(1,1,pedidoActualizado);
        assertNotNull(pedido1);
        assertEquals(1,pedido1.getIdPedido());
        assertEquals(12.56, pedido1.getTotal());
        verify(pedidoRepository).save(any(Pedido.class));
        verify(clienteRepository).findById(1);
    }

    @Test
    public void testDelete(){
        when(pedidoRepository.existsById(1)).thenReturn(true);
        pedidoService.delete(1);
        verify(pedidoRepository,times(1)).deleteById(1);
    }




}
