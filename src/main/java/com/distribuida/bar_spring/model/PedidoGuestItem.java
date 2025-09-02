package com.distribuida.bar_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos_guest_items")
public class PedidoGuestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int idItem;

    @ManyToOne
    @JoinColumn(name = "pedido_guest_id")
    private PedidoGuest pedidoGuest;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "total")
    private Double total;

    public PedidoGuestItem (){}

    public PedidoGuestItem(int idItem, PedidoGuest pedidoGuest, Producto producto, int cantidad, Double precioUnitario, Double total) {
        this.idItem = idItem;
        this.pedidoGuest = pedidoGuest;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public PedidoGuest getPedidoGuest() {
        return pedidoGuest;
    }

    public void setPedidoGuest(PedidoGuest pedidoGuest) {
        this.pedidoGuest = pedidoGuest;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}