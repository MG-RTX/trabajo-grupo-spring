package com.distribuida.bar_spring.service.util;

import com.distribuida.bar_spring.model.Carrito;
import com.distribuida.bar_spring.model.CarritoItem;
import com.distribuida.bar_spring.model.Factura;
import com.distribuida.bar_spring.model.FacturaDetalle;

import java.util.Date;

public class CheckoutMapper {

    private CheckoutMapper(){}

    public static Factura construirFacturaDesdeCarrito(
            Carrito carrito, String numFactura, double tasaIva
    ){
        Factura f = new Factura();
        f.setNumFactura(numFactura);
        f.setFecha(new Date());
        f.setCliente(carrito.getCliente());

        double subtotal = carrito.getItems().stream()
                .mapToDouble(i -> i.getTotal().doubleValue())
                .sum();

        double iva = Math.max(0, subtotal) * tasaIva;
        double total = subtotal + iva;
        f.setTotalNeto(subtotal);
        f.setIva(iva);
        f.setTotal(total);
        return f;
    }

    public static FacturaDetalle construirDetalle(Factura factura, CarritoItem item){
        FacturaDetalle d = new FacturaDetalle();
        d.setFactura(factura);
        d.setPedido(item.getPedido());
        d.setCantidad(item.getCantidad());
        d.setSubtotal(item.getTotal().doubleValue());
        return d;
    }
}
