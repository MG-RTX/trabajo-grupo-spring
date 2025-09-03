package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.FacturaDetalle;

import java.util.List;

public interface FacturaDetalleService {
    public List<FacturaDetalle> findAll();
    public FacturaDetalle findOne(int id);
    public FacturaDetalle save(FacturaDetalle facturaDetalle);
    public FacturaDetalle update(int id, int idFactura, int idPedido, FacturaDetalle facturaDetalle);
    public void delete(int id);

}
