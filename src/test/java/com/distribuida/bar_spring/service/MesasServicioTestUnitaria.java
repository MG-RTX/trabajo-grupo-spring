package com.distribuida.bar_spring.service;
import com.distribuida.bar_spring.dao.MesasRepository;
import com.distribuida.bar_spring.model.Mesas;
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
public class MesasServicioTestUnitaria {

    @Mock
    private MesasRepository mesasRepository;

    @InjectMocks
    private MesasServiceImpl mesasService;

    private Mesas mesas;

    @BeforeEach
    public void setUp(){
        mesas = new Mesas();
        mesas.setIdMesa(1);
        mesas.setNumeroMesa(1);
        mesas.setCapacidad(10);
        mesas.setUbicacion("Casa");
    }

    @Test
    public void testfindAll(){
        when(mesasRepository.findAll()).thenReturn(java.util.Arrays.asList(mesas));
        List<Mesas> mesas = mesasService.findAll();
        assertNotNull(mesas);
        assertEquals(1,mesas.size());
        verify(mesasRepository, times(1)).findAll();
    }

    @Test
    public void testfindOneExistente(){
        when(mesasRepository.findById(1)).thenReturn(java.util.Optional.of(mesas));
        Mesas mesaExistente = mesasService.findOne(1);
        assertNotNull(mesaExistente);
        assertEquals(1,mesaExistente.getNumeroMesa());
    }

    @Test
    public void testfindOneNoExistente(){
        when(mesasRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Mesas mesa = mesasService.findOne(1);
        assertEquals(null,mesa);
    }

    @Test
    public void testsave(){
        when(mesasRepository.save(mesas)).thenReturn(mesas);
        Mesas mesaExistente = mesasService.save(mesas);
        assertNotNull(mesaExistente.getIdMesa());
        assertEquals(1,mesaExistente.getNumeroMesa());
    }

    @Test
    public void testupdateExistente(){
        Mesas mesaActualizado = new Mesas();
        mesaActualizado.setIdMesa(1);
        mesaActualizado.setNumeroMesa(1);
        mesaActualizado.setCapacidad(10);
        mesaActualizado.setUbicacion("Casa");
        when(mesasRepository.findById(1)).thenReturn(java.util.Optional.of(mesas));
        when(mesasRepository.save(mesas)).thenReturn(mesaActualizado);
        Mesas mesaExistente = mesasService.update(1,mesaActualizado);
        assertNotNull(mesaExistente);
        assertEquals(1,mesaExistente.getNumeroMesa());
        verify(mesasRepository, times(1)).save(mesaActualizado);
    }

    @Test
    public void testupdateNoExistente(){
        Mesas mesaNuevo = new Mesas();
        mesaNuevo.setIdMesa(1);
        mesaNuevo.setNumeroMesa(1);
        mesaNuevo.setCapacidad(10);
        mesaNuevo.setUbicacion("Casa");
        when(mesasRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Mesas mesaExistente = mesasService.update(1,mesaNuevo);
    }

    @Test
    public void testdeleteExistente(){
        when(mesasRepository.existsById(1)).thenReturn(true);
        mesasService.delete(1);
        verify(mesasRepository,times(1)).deleteById(1);
    }

    @Test
    public void testdeleteNoExistente(){
        when(mesasRepository.existsById(1)).thenReturn(false);
        mesasService.delete(1);
        verify(mesasRepository,times(0)).deleteById(1);
    }

}
