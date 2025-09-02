package com.distribuida.bar_spring.service;



import com.distribuida.bar_spring.dao.PedidoGuestRepository;
import com.distribuida.bar_spring.dao.ProductoRepository;
import com.distribuida.bar_spring.model.PedidoGuest;
import com.distribuida.bar_spring.model.PedidoGuestItem;
import com.distribuida.bar_spring.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class PedidoGuestServiceImpl implements PedidoGuestService {

    @Autowired
    private PedidoGuestRepository pedidoGuestRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public PedidoGuest createOrGet(String token) {
        Optional<PedidoGuest> existingPedido = pedidoGuestRepository.findByToken(token);

        if (existingPedido.isPresent()) {
            return existingPedido.get();
        }

        PedidoGuest nuevoPedido = new PedidoGuest();
        nuevoPedido.setToken(token);
        nuevoPedido.setFechaCreacion(new Date());
        nuevoPedido.setFechaActualizacion(new Date());
        nuevoPedido.setSubtotal(0.0);
        nuevoPedido.setImpuestos(0.0);
        nuevoPedido.setTotal(0.0);

        return pedidoGuestRepository.save(nuevoPedido);
    }

    @Override
    public Optional<PedidoGuest> getByToken(String token) {
        return pedidoGuestRepository.findByToken(token);
    }

    @Override
    public PedidoGuest addItem(String token, int productoId, int cantidad) {
        PedidoGuest pedido = createOrGet(token);
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }

        Producto producto = productoOpt.get();

        // Verificar si el producto ya existe en el pedido
        Optional<PedidoGuestItem> existingItem = pedido.getItems().stream()
                .filter(item -> item.getProducto().getIdProducto() == productoId)
                .findFirst();

        if (existingItem.isPresent()) {
            // Actualizar cantidad si ya existe
            PedidoGuestItem item = existingItem.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setTotal(item.getPrecioUnitario() * item.getCantidad());
        } else {
            // Crear nuevo item
            PedidoGuestItem newItem = new PedidoGuestItem();
            newItem.setPedidoGuest(pedido);
            newItem.setProducto(producto);
            newItem.setCantidad(cantidad);
            newItem.setPrecioUnitario(producto.getPrecio());
            newItem.setTotal(producto.getPrecio() * cantidad);

            pedido.getItems().add(newItem);
        }

        // Recalcular totales
        return calcularTotales(pedido);
    }

    @Override
    public PedidoGuest updateItem(String token, int itemId, int cantidad) {
        Optional<PedidoGuest> pedidoOpt = pedidoGuestRepository.findByToken(token);

        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado");
        }

        PedidoGuest pedido = pedidoOpt.get();

        Optional<PedidoGuestItem> itemOpt = pedido.getItems().stream()
                .filter(item -> item.getIdItem() == itemId)
                .findFirst();

        if (itemOpt.isEmpty()) {
            throw new RuntimeException("Item no encontrado");
        }

        PedidoGuestItem item = itemOpt.get();
        item.setCantidad(cantidad);
        item.setTotal(item.getPrecioUnitario() * cantidad);

        // Recalcular totales
        return calcularTotales(pedido);
    }

    @Override
    public void removeItem(String token, int itemId) {
        Optional<PedidoGuest> pedidoOpt = pedidoGuestRepository.findByToken(token);

        if (pedidoOpt.isPresent()) {
            PedidoGuest pedido = pedidoOpt.get();
            pedido.getItems().removeIf(item -> item.getIdItem() == itemId);
            calcularTotales(pedido);
        }
    }

    @Override
    public void clear(String token) {
        Optional<PedidoGuest> pedidoOpt = pedidoGuestRepository.findByToken(token);

        if (pedidoOpt.isPresent()) {
            PedidoGuest pedido = pedidoOpt.get();
            pedido.getItems().clear();
            pedido.setSubtotal(0.0);
            pedido.setImpuestos(0.0);
            pedido.setTotal(0.0);
            pedido.setFechaActualizacion(new Date());
            pedidoGuestRepository.save(pedido);
        }
    }

    @Override
    public PedidoGuest calcularTotales(PedidoGuest pedido) {
        double subtotal = pedido.getItems().stream()
                .mapToDouble(PedidoGuestItem::getTotal)
                .sum();

        double impuestos = subtotal * 0.12; // 12% IVA
        double total = subtotal + impuestos;

        pedido.setSubtotal(subtotal);
        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);
        pedido.setFechaActualizacion(new Date());

        return pedidoGuestRepository.save(pedido);
    }
}