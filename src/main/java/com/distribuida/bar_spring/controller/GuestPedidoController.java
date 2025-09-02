package com.distribuida.bar_spring.controller;
import com.distribuida.bar_spring.model.PedidoGuest;
import com.distribuida.bar_spring.service.PedidoGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest/pedido")
public class GuestPedidoController {

    @Autowired
    private PedidoGuestService pedidoGuestService;

    @PostMapping
    public ResponseEntity<PedidoGuest> createOrGet(@RequestParam String token) {
        PedidoGuest pedido = pedidoGuestService.createOrGet(token);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<PedidoGuest> get(@RequestParam String token) {
        return pedidoGuestService.getByToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/items")
    public ResponseEntity<PedidoGuest> addItem(
            @RequestParam String token,
            @RequestBody AddItemRequest request) {
        PedidoGuest pedido = pedidoGuestService.addItem(token, request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<PedidoGuest> updateItem(
            @PathVariable int itemId,
            @RequestParam String token,
            @RequestBody UpdateItemRequest request) {
        PedidoGuest pedido = pedidoGuestService.updateItem(token, itemId, request.getCantidad());
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable int itemId,
            @RequestParam String token) {
        pedidoGuestService.removeItem(token, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clear(@RequestParam String token) {
        pedidoGuestService.clear(token);
        return ResponseEntity.noContent().build();
    }

    // Clases para los requests CON GETTERS Y SETTERS
    public static class AddItemRequest {
        private int productoId;
        private int cantidad;

        // GETTERS Y SETTERS
        public int getProductoId() {
            return productoId;
        }

        public void setProductoId(int productoId) {
            this.productoId = productoId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }

    public static class UpdateItemRequest {
        private int cantidad;

        // GETTERS Y SETTERS
        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}