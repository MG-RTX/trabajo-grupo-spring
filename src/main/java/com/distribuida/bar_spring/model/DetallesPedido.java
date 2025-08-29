package com.distribuida.bar_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="detalles_pedido")
public class DetallesPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle_pedido")
    private int idDetalle;

    @Column(name="subtotal")
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;  // Cambiado a Cliente (con mayúscula)

    public DetallesPedido() {
    }

    public DetallesPedido(int idDetalle, Double subtotal, Pedido pedido, Producto producto, Cliente cliente) {  // Cambiado a Cliente
        this.idDetalle = idDetalle;
        this.subtotal = subtotal;
        this.pedido = pedido;
        this.producto = producto;
        this.cliente = cliente;
    }

    // Getters y Setters (cambiados los tipos de cliente)
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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

    public Cliente getCliente() {  // Cambiado a Cliente
        return cliente;
    }

    public void setCliente(Cliente cliente) {  // Cambiado a Cliente
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DetallesPedido{" +
                "idDetalle=" + idDetalle +
                ", subtotal=" + subtotal +
                ", pedido=" + pedido +
                ", producto=" + producto +
                ", cliente=" + cliente +
                '}';
    }
}