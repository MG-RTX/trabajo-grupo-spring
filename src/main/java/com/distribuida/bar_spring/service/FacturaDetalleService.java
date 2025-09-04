package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.FacturaDetalle;
import java.util.List;

public interface FacturaDetalleService {
    List<FacturaDetalle> findAll();
    FacturaDetalle findOne(int id);
    FacturaDetalle save(FacturaDetalle facturaDetalle);
    FacturaDetalle update(int id, int idFactura, int idPedido, FacturaDetalle facturaDetalle);
    void delete(int id);
}