package com.distribuida.bar_spring.controller;

import com.distribuida.bar_spring.model.DetallesPedido;
import com.distribuida.bar_spring.service.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles_pedido")
public class DetallesPedidoController {  // Cambiado a singular (coincide con archivo)

    @Autowired
    private DetallesPedidoService detallesPedidoService;

    @GetMapping
    public ResponseEntity<List<DetallesPedido>> findAll(){
        List<DetallesPedido> detallesPedidos = detallesPedidoService.findAll();
        return ResponseEntity.ok(detallesPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedido> findOne(@PathVariable int id){
        DetallesPedido detallesPedido = detallesPedidoService.findOne(id);
        if (detallesPedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallesPedido);
    }

    @PostMapping
    public ResponseEntity<DetallesPedido> save(@RequestBody DetallesPedido detallesPedido){
        DetallesPedido detallesPedidoNuevo = detallesPedidoService.save(detallesPedido);
        return ResponseEntity.ok(detallesPedidoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallesPedido> update(@PathVariable int id, @RequestBody DetallesPedido detallesPedido){
        DetallesPedido detallesPedidoActualizado = detallesPedidoService.update(id, detallesPedido);  // Cambiado nombre variable
        if(detallesPedidoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallesPedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        detallesPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}