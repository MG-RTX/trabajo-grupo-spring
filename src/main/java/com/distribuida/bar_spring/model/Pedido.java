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
    @Column(name="total")
    private Double total;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private cliente cliente;

    public Pedido(){}

    public Pedido(int idPedido, Date fechaPedido, Double total, cliente cliente) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.cliente = cliente;
    }

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaPedido=" + fechaPedido +
                ", total=" + total +
                ", cliente=" + cliente +
                '}';
    }
}
