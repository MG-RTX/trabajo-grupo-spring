package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.promocionesRepository;
import com.distribuida.bar_spring.model.promociones;
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
public class promocionesServicioTestUnitaria {

    @Mock
    private promocionesRepository promocionesRepository;

    @InjectMocks
    private promocionesServiceImpl promocionesService;

    private promociones promociones;

    @BeforeEach
    public void setUp(){
        promociones = new promociones();
        promociones.setIdPromocion(1);
        promociones.setNombrepromocion("San ValenISMAC");
        promociones.setDescripcion("Tortolos 3 x 1");
        promociones.setFechaInicio("Lunes");
        promociones.setFechaFin("Martes");
        promociones.setActivo("Activo");
    }

    @Test
    public void testfindAll(){
        when(promocionesRepository.findAll()).thenReturn(java.util.Arrays.asList(promociones));
        List<promociones> promociones1 = promocionesService.findAll();
        assertNotNull(promociones1);
        assertEquals(1,promociones1.size());
        verify(promocionesRepository, times(1)).findAll();
    }

    @Test
    public void testfindOneExistente(){
        when(promocionesRepository.findById(1)).thenReturn(java.util.Optional.of(promociones));
        promociones promociones1 = promocionesService.findOne(1);
        assertNotNull(promociones1);
        assertEquals(1,promociones1.getIdPromocion());
    }

    @Test
    public void testfindOneNoExistente(){
        when(promocionesRepository.findById(1)).thenReturn(java.util.Optional.empty());
        promociones promociones1 = promocionesService.findOne(1);
        assertEquals(null,promociones1);
    }

    @Test
    public void testsave(){
        when(promocionesRepository.save(promociones)).thenReturn(promociones);
        promociones promociones1 = promocionesService.save(promociones);
        assertNotNull(promociones1.getIdPromocion());
        assertEquals(1,promociones1.getIdPromocion());
    }

    @Test
    public void testupdateExistente(){
        promociones promocionesActualizado = new promociones();
        promocionesActualizado.setIdPromocion(1);
        promocionesActualizado.setNombrepromocion("San ValenISMAC");
        promocionesActualizado.setDescripcion("Tortolos 3 x 1");
        promocionesActualizado.setFechaInicio("Lunes");
        promocionesActualizado.setFechaFin("Martes");
        when(promocionesRepository.findById(1)).thenReturn(java.util.Optional.of(promociones));
        when(promocionesRepository.save(promociones)).thenReturn(promocionesActualizado);
        promociones promociones1 = promocionesService.update(1,promocionesActualizado);
        assertNotNull(promociones1);
        assertEquals(1,promociones1.getIdPromocion());
        verify(promocionesRepository, times(1)).save(promocionesActualizado);
    }

    @Test
    public void testupdateNoExistente(){
        promociones promocionesNuevo = new promociones();
        promocionesNuevo.setIdPromocion(1);
        promocionesNuevo.setNombrepromocion("San ValenISMAC");
        promocionesNuevo.setDescripcion("Tortolos 3 x 1");
        promocionesNuevo.setFechaInicio("Lunes");
        promocionesNuevo.setFechaFin("Martes");
        when(promocionesRepository.findById(1)).thenReturn(java.util.Optional.empty());
        promociones promociones1 = promocionesService.update(1,promocionesNuevo);
    }

    @Test
    public void testdeleteExistente(){
        when(promocionesRepository.existsById(1)).thenReturn(true);
        promocionesService.delete(1);
        verify(promocionesRepository,times(1)).deleteById(1);
    }
    @Test
    public void testdeleteNoExistente(){
        when(promocionesRepository.existsById(1)).thenReturn(false);
        promocionesService.delete(1);
        verify(promocionesRepository,times(0)).deleteById(1);
    }

}
