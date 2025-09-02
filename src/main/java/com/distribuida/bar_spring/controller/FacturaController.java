package com.distribuida.bar_spring.controller;

import com.distribuida.bar_spring.model.Factura;
import com.distribuida.bar_spring.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> findAll() {
        List<Factura> facturas = facturaService.findAll();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findOne(@PathVariable int id) {
        Factura factura = facturaService.findOne(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Factura> findByPedido(@PathVariable int idPedido) {
        Factura factura = facturaService.findByPedido(idPedido);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PostMapping("/generar")
    public ResponseEntity<Factura> generarFactura(@RequestBody GenerarFacturaRequest request) {
        Factura factura = facturaService.generarFactura(request.getPedidoId());
        if (factura == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(factura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Clase interna para el request de generar factura
    public static class GenerarFacturaRequest {
        private int pedidoId;

        public int getPedidoId() {
            return pedidoId;
        }

        public void setPedidoId(int pedidoId) {
            this.pedidoId = pedidoId;
        }
    }
}