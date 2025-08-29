package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private int idPedido;

    @Column(name="fecha_pedido")
    private Date fechaPedido;

    @Column(name = "pago")
    private String pago;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column (name = "cantidad")
    private int cantidad;  // Cambiado a int

    @Column (name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;  // Cambiado a Cliente

    public Pedido(){}

    public Pedido(int idPedido, Date fechaPedido, String pago, Producto producto, int cantidad, Double total, Cliente cliente) {  // Cambiados tipos
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.pago = pago;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.cliente = cliente;
    }

    // Getters y Setters (cambiados los tipos)
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {  // Cambiado a int
        return cantidad;
    }

    public void setCantidad(int cantidad) {  // Cambiado a int
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {  // Cambiado a Cliente
        return cliente;
    }

    public void setCliente(Cliente cliente) {  // Cambiado a Cliente
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaPedido=" + fechaPedido +
                ", pago='" + pago + '\'' +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", cliente=" + cliente +
                '}';
    }
}
