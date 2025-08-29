package com.distribuida.bar_spring.controller;

import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> pedido = pedidoService.findAll();
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findOne(@PathVariable int id){
        Pedido pedido = pedidoService.findOne(id);
        if (pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido){
        Pedido pedidoNuevo = pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable int id, @RequestBody Pedido pedido){
        Pedido pedidoActualizada = pedidoService.update(id, pedido);
        if (pedidoActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}