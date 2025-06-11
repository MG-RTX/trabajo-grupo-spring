package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.dao.clienteRepository;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.cliente;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class PedidoServiceImpl implements  PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private clienteRepository clienteRepository;


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
    public Pedido update(int id, int idCliente, Pedido pedido) {
        Pedido pedidoExistente =findOne(id);
        Optional<cliente> clienteExistente = clienteRepository.findById(idCliente);
        if (pedidoExistente == null){
            return null;
        }

        pedidoExistente.setCliente(clienteExistente.orElse(null));
        pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        pedidoExistente.setTotal(pedido.getTotal());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void delete(int id) {
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
        }

    }
}
