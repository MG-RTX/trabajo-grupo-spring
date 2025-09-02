package com.distribuida.bar_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos_guest")
public class PedidoGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_guest")
    private int idPedidoGuest;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "impuestos")
    private Double impuestos;

    @Column(name = "total")
    private Double total;

    // En PedidoGuest.java
    @OneToMany(mappedBy = "pedidoGuest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore // ← Evitar serialización circular
    private List<PedidoGuestItem> items = new ArrayList<>();

    // Constructores, getters y setters
    public PedidoGuest() {
        this.fechaCreacion = new Date();
        this.fechaActualizacion = new Date();
    }

    public List<PedidoGuestItem> getItems() {
        return items;
    }

    public void setItems(List<PedidoGuestItem> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdPedidoGuest() {
        return idPedidoGuest;
    }

    public void setIdPedidoGuest(int idPedidoGuest) {
        this.idPedidoGuest = idPedidoGuest;
    }
}