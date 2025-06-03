package com.distribuida.bar_spring.model;

import java.util.Date;

public class Pedido {

    private int idPedido;
    private Date fechaPedido;
    private Double total;
    private String estado;
    private cliente cliente;

    public Pedido(){}

    public Pedido(int idPedido, Date fechaPedido, Double total, String estado, cliente cliente) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
                ", estado='" + estado + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
