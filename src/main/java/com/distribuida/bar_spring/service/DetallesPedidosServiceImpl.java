package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.DetallesPedidoRepository;
import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.dao.ProductoRepository;
import com.distribuida.bar_spring.model.DetallesPedido;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DetallesPedidosServiceImpl implements DetallesPedidoService{

    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<DetallesPedido> findAll() {
        return detallesPedidoRepository.findAll();
    }

    @Override
    public DetallesPedido findOne(int id) {
        Optional<DetallesPedido> detallesPedidos = detallesPedidoRepository.findById(id);
        return detallesPedidos.orElse(null);
    }

    @Override
    public DetallesPedido save(DetallesPedido detallesPedido) {
        return detallesPedidoRepository.save(detallesPedido);
    }

    @Override
    public DetallesPedido update(int id, int idPedido, int idProducto, DetallesPedido detallesPedido) {
        DetallesPedido detallesPedidoExistente =findOne(id);
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);
        Optional<Producto> productoExistente = productoRepository.findById(idProducto);
        if (detallesPedidoExistente == null){
            return null;
        }

        detallesPedidoExistente.setPedido(pedidoExistente.orElse(null));
        detallesPedidoExistente.setProducto(productoExistente.orElse(null));
        detallesPedidoExistente.setCantidad(detallesPedido.getCantidad());
        detallesPedidoExistente.setPrecioUnitario(detallesPedido.getPrecioUnitario());
        detallesPedidoExistente.setSubtotal(detallesPedido.getSubtotal());

        return detallesPedidoRepository.save(detallesPedidoExistente);
    }

    @Override
    public void delete(int id) {
        if(detallesPedidoRepository.existsById(id)){
            detallesPedidoRepository.deleteById(id);
        }

    }
}
