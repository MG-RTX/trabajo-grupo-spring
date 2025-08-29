package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findOne(int id) {
        Optional<Pedido> pedidos = pedidoRepository.findById(id);
        return pedidos.orElse(null);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(int id, Pedido pedido) {
        Pedido pedidoExistente = findOne(id);
        if (pedidoExistente == null){
            return null;
        }

        pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        pedidoExistente.setPago(pedido.getPago());
        pedidoExistente.setProducto(pedido.getProducto());
        pedidoExistente.setCantidad(pedido.getCantidad());
        pedidoExistente.setTotal(pedido.getTotal());
        pedidoExistente.setCliente(pedido.getCliente());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void delete(int id) {
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
        }
    }
}
