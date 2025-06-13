package com.distribuida.bar_spring.service;
import com.distribuida.bar_spring.dao.clienteRepository;
import com.distribuida.bar_spring.model.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class clienteServicioTestUntiaria {

    @Mock
    private clienteRepository clienteRepository;

    @InjectMocks
    private clienteServiceImpl clienteService;

    private cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new cliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Marco");
        cliente.setCarrera("Software");
        cliente.setCorreo("<EMAIL>");
        cliente.setFechaRegistro(new java.util.Date());
    }

    @Test
    public void testfindAll(){
        when(clienteRepository.findAll()).thenReturn(java.util.Arrays.asList(cliente));
        List<cliente> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(1,clientes.size());
        verify(clienteRepository,times(1)).findAll();
    }

    @Test
    public void testfindOneExistente(){
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        cliente clienteExistente = clienteService.findOne(1);
        assertNotNull(clienteExistente);
        assertEquals("Marco",clienteExistente.getNombre());
    }

    @Test
    public void testfindOneNoExistente(){
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());
        cliente cliente = clienteService.findOne(1);
        assertEquals(null,cliente);
    }

    @Test
    public void testsave(){
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        cliente clienteExistente = clienteService.save(cliente);
        assertNotNull(clienteExistente.getIdCliente());
        assertEquals("Marco",clienteExistente.getNombre());
    }

    @Test
    public void testupdateExistente(){
        cliente clienteActualizado = new cliente();

        clienteActualizado.setNombre("Alexadra");
        clienteActualizado.setCarrera("Software");
        clienteActualizado.setCorreo("<EMAIL>");
        clienteActualizado.setFechaRegistro(new java.util.Date());

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(clienteActualizado);

        cliente clienteExistente = clienteService.update(1,cliente);
        assertNotNull(clienteExistente);
        assertEquals("Alexadra",clienteExistente.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testupdateNoExistente(){
        cliente clienteNuevo = new cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());
        cliente clienteExistente = clienteService.update(1,clienteNuevo);
        assertEquals(null,clienteExistente);
        verify(clienteRepository,times(0)).save(clienteNuevo);
    }

    @Test
    public void testdeleteExistente(){
        when(clienteRepository.existsById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteRepository,times(1)).deleteById(1);
    }

    @Test
    public void testdeleteNoExistente(){
        when(clienteRepository.existsById(1)).thenReturn(false);
        clienteService.delete(1);
        verify(clienteRepository,times(0)).deleteById(1);
    }

}
