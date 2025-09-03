package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.*;
import com.distribuida.bar_spring.model.Factura;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.service.util.CheckoutMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GuestCheckoutServiceImpl implements GuestCheckoutService{

    private final CarritoRepository carritoRepository;
    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;

    private static final double IVA = 0.15d;
    private final ProductoRepository productoRepository;

    public GuestCheckoutServiceImpl(
            CarritoRepository carritoRepository,
            FacturaRepository facturaRepository,
            FacturaDetalleRepository facturaDetalleRepository,
            PedidoRepository pedidoRepository,
            ProductoRepository productoRepository){
        this.carritoRepository = carritoRepository;
        this.facturaRepository = facturaRepository;
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.productoRepository = productoRepository;
    }

    @Override
@Transactional
public Factura checkoutByToken(String token){
    var carrito = carritoRepository.findByToken(token)
            .orElseThrow(()-> new IllegalArgumentException("No existe carrito para el token"));
    if (carrito.getItems() == null || carrito.getItems().isEmpty()){
        throw new IllegalArgumentException("El carrito esta vacio");
    }
    for (var item: carrito.getItems()){
        var pedido = item.getPedido();
        var producto = item.getProducto();
        if (producto.getStock() < item.getCantidad()){
            throw new IllegalArgumentException("Stock insufuciente para:" + producto.getNombreProducto());
        }
    }
    for (var item: carrito.getItems()){
        var pedido = item.getPedido();
        var producto = item.getProducto();
        producto.setStock(producto.getStock() - item.getCantidad());
        productoRepository.save(producto);
    }

    String numFactura = "F-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            .format(LocalDateTime.now());

    var factura = CheckoutMapper.construirFacturaDesdeCarrito(carrito, numFactura,IVA);

    var factura = facturaRepository.save(factura);
    for (var item: carrito.getItems()){
        var det = CheckoutMapper.construirDetalle(factura, item);
        facturaDetalleRepository.save(det);
    }

    carrito.getItems().clear();
    carritoRepository.save(carrito);

    return factura;


    }
}
