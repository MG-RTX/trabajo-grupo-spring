package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.DetallesPedidoRepository;
import com.distribuida.bar_spring.model.DetallesPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesPedidoServiceImpl implements DetallesPedidoService {  // Cambiado a DetallesPedidoServiceImpl

    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    @Override
    public List<DetallesPedido> findAll() {
        return detallesPedidoRepository.findAll();
    }

    @Override
    public DetallesPedido findOne(int id) {
        Optional<DetallesPedido> detallesPedido = detallesPedidoRepository.findById(id);  // Cambiado nombre de variable
        return detallesPedido.orElse(null);
    }

    @Override
    public DetallesPedido save(DetallesPedido detallesPedido) {
        return detallesPedidoRepository.save(detallesPedido);
    }

    @Override
    public DetallesPedido update(int id, DetallesPedido detallesPedido) {
        DetallesPedido detallesPedidoExistente = findOne(id);
        if (detallesPedidoExistente == null){
            return null;
        }

        detallesPedidoExistente.setCliente(detallesPedido.getCliente());
        detallesPedidoExistente.setSubtotal(detallesPedido.getSubtotal());
        detallesPedidoExistente.setPedido(detallesPedido.getPedido());
        detallesPedidoExistente.setProducto(detallesPedido.getProducto());

        return detallesPedidoRepository.save(detallesPedidoExistente);
    }

    @Override
    public void delete(int id) {
        if(detallesPedidoRepository.existsById(id)){
            detallesPedidoRepository.deleteById(id);
        }
    }
}