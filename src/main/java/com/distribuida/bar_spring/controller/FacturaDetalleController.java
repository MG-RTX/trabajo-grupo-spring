package com.distribuida.bar_spring.controller;

import com.distribuida.bar_spring.model.FacturaDetalle;
import com.distribuida.bar_spring.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/factura_detalle")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> findAll(){
        List<FacturaDetalle> facturaDetalles = facturaDetalleService.findAll();
        return ResponseEntity.ok(facturaDetalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id){
        FacturaDetalle facturaDetalle = facturaDetalleService.findOne(id);
        if (facturaDetalle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDetalle);
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle){
        FacturaDetalle facturaDetalleNuevo = facturaDetalleService.save(facturaDetalle);
        return ResponseEntity.ok(facturaDetalleNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(@PathVariable int id, @PathVariable int idFactura, @PathVariable int idPedido, @RequestBody FacturaDetalle facturaDetalle){
        FacturaDetalle facturaDetalleActualizada = facturaDetalleService.update(id,idFactura,idPedido,facturaDetalle);
        if (facturaDetalleActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDetalleActualizada);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
