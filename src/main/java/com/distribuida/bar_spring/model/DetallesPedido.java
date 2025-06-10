package com.distribuida.bar_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="detalles_pedido")
public class DetallesPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle_pedido")
    private int idDetalle;
    @Column(name="cantidad")
    private int cantidad;
    @Column(name="precio_unitario")
    private Double precioUnitario;
    @Column(name="subtotal")
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    public DetallesPedido() {
    }

    public DetallesPedido(int idDetalle, int cantidad, Double precioUnitario, Double subtotal, Pedido pedido, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.pedido = pedido;
        this.producto = producto;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallesPedido{" +
                "idDetalle=" + idDetalle +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                ", pedido=" + pedido +
                ", producto=" + producto +
                '}';
    }
}
