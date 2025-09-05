package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.*;
import com.distribuida.bar_spring.model.Carrito;
import com.distribuida.bar_spring.model.CarritoItem;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarritoServiceImpl implements CarritoService{

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;

    private static  final BigDecimal IVA = new BigDecimal("0.15");

    public CarritoServiceImpl(CarritoRepository carritoRepository
    , CarritoItemRepository carritoItemRepository
    , ClienteRepository clienteRepository
    , ProductoRepository productoRepository,
                              PedidoRepository pedidoRepository){
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public Carrito getOrCreatedByClienteId(int clienteId, String token) {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado" + clienteId));

        var carritoOpt = carritoRepository.findByCliente(cliente);
        if(carritoOpt.isPresent()) return carritoOpt.get();

        var carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setToken(token);
        carrito.recomprobacionTotalesCompat();
        return carritoRepository.save(carrito);

    }

    @Override
    public Carrito addItem(int clienteId, int productoId, int cantidad){
        if(cantidad <=0) throw new IllegalArgumentException("Cantidad debe ser <0");

        var carrito = getOrCreatedByClienteId(clienteId,null);
        var producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no Encontrado" + productoId));
        var itemOpt = carritoItemRepository.findByCarritoAndProducto(carrito, producto);
        if (itemOpt.isPresent()){
            var item = itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(producto.getPrecio()));
            item.calcTotal();

            carritoItemRepository.save(item);
        }else {
            var item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(producto);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(producto.getPrecio()));
            item.calcTotal();
            carrito.getItems().add(item);
        }
        carrito.recomputarTotales(IVA);

        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito updateItemCantidad(int clienteId, long carritoItemId, int nuevaCantidad){
        if(nuevaCantidad < 0)throw new IllegalArgumentException("Cantidad no puede ser negativa");

        var carrito = getByClienteId(clienteId);
        var item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item no Encontrado"+ carritoItemId));
        if(!item.getCarrito().getIdCarrito().equals(carrito.getIdCarrito())){
            throw  new IllegalArgumentException("Item no pertence al carrito del cliente");
        }
        if(nuevaCantidad == 0){
            carrito.getItems().remove(item);
            carritoItemRepository.delete(item);
        }else {
            item.setCantidad(nuevaCantidad);
            carritoItemRepository.save(item);
        }
        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);

    }


    @Override
    @Transactional
    public void removeItem(int clienteId, long carritoItemId) {
        updateItemCantidad(clienteId, carritoItemId,0);

    }

    @Override
    @Transactional
    public void clear(int clienteId) {
        var carrito = getByClienteId(clienteId);
        carrito.getItems().clear();
        carrito.recomputarTotales(IVA);
        carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito getByClienteId(int clienteId) {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no Encontrado" + clienteId));
        return carritoRepository.findByCliente(cliente)
                .orElseGet(() -> {
                    var c = new Carrito();
                    c.setCliente(cliente);
                    return c;
                });
    }

    @Override
    @Transactional
    public Carrito getOrCreatedByToken(String token) {
        if(token == null || token.isBlank()) {
            token = UUID.randomUUID().toString();
        }


        Optional<Carrito> existente = carritoRepository.findByToken(token);
        if (existente.isPresent()) return existente.get();

        try {
            Carrito c = new Carrito();
            c.setToken(token);
            c.setSubtotal(BigDecimal.ZERO);
            c.setDescuento(BigDecimal.ZERO);
            c.setImpuestos(BigDecimal.ZERO);
            c.setTotal(BigDecimal.ZERO);
            return carritoRepository.save(c);
        } catch (DataIntegrityViolationException e) {
            // Si otro request creó el mismo token concurrentemente
            return carritoRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Error inesperado al crear carrito"));
        }
    }

    @Override
    @Transactional
    public Carrito addItem(String token, int productoId, int cantidad){
        if(cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
        var carrito = getOrCreatedByToken(token);
        var producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no Encontrado: " + productoId));
        var itemOpt = carritoItemRepository.findByCarritoAndProducto(carrito,producto);
        if (itemOpt.isPresent()){
            var item = itemOpt.get();
            item.setCantidad(item.getCantidad()+cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(producto.getPrecio()));
            item.calcTotal();
            carritoItemRepository.save(item);
        }else{
            var item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(producto);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(producto.getPrecio()));
            item.calcTotal();
        }
        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito updateItemCantidad(String token, long carritoItemId, int nuevaCantidad) {
        var carrito = getOrCreatedByToken(token);
        var item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item no encontrado: "+ carritoItemId));

        if(nuevaCantidad <= 0){
            carrito.getItems().remove(item);
            carritoItemRepository.delete(item);
        }else{
            item.setCantidad(nuevaCantidad);
            item.calcTotal();
            carritoItemRepository.save(item);
        }
        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    @Override
    public void removeItem(String token, long carritoItemId) {

    }

    @Override
    @Transactional
    public void clearByToken(String token) {
        var carrito = getOrCreatedByToken(token);
        carrito.getItems().clear();
        carrito.setSubtotal(BigDecimal.ZERO);
        carrito.setDescuento(BigDecimal.ZERO);
        carrito.setImpuestos(BigDecimal.ZERO);
        carrito.setTotal(BigDecimal.ZERO);
        carritoRepository.save(carrito);

    }

    @Override
    @Transactional
    public Carrito getByToken(String token) {
        return carritoRepository.findByToken(token)
                .orElseGet(() -> {
                    var c = new Carrito();
                    c.setToken(token);
                    c.setSubtotal(BigDecimal.ZERO);
                    c.setDescuento(BigDecimal.ZERO);
                    c.setImpuestos(BigDecimal.ZERO);
                    c.setTotal(BigDecimal.ZERO);
                    return c;
                });
    }

}
