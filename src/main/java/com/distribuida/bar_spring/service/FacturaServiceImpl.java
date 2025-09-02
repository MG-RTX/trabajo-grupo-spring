package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.dao.FacturaRepository;
import com.distribuida.bar_spring.dao.PedidoRepository;
import com.distribuida.bar_spring.model.Factura;
import com.distribuida.bar_spring.model.Pedido;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findOne(int id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura findByPedido(int idPedido) {
        return facturaRepository.findByPedidoIdPedido(idPedido);
    }

    @Override
    public Factura generarFactura(int pedidoId) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
        if (pedidoOpt.isEmpty()) {
            return null;
        }

        Pedido pedido = pedidoOpt.get();

        // Crear factura basada en el pedido
        Factura factura = new Factura();
        factura.setNumFactura(generarNumeroFactura());
        factura.setFecha(new Date());
        factura.setTotalNeto(pedido.getTotal() / 1.12); // Asumiendo 12% IVA
        factura.setIva(pedido.getTotal() * 0.12);
        factura.setTotal(pedido.getTotal());
        factura.setCliente(pedido.getCliente());
        factura.setPedido(pedido);

        return facturaRepository.save(factura);
    }

    @Override
    public void delete(int id) {
        facturaRepository.deleteById(id);
    }

    private String generarNumeroFactura() {
        // Lógica para generar número de factura único
        return "FAC-" + System.currentTimeMillis();
    }
}