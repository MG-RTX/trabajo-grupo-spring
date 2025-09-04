package com.distribuida.bar_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private int idProducto;

    @Column(name="portada")
    private String portada;

    @Column(name="nombre_producto")
    private String nombreProducto;

    @Column(name = "precio")
    private Double precio;

    @Column(name="stock")
    private int stock ;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    @JsonIgnore
    private Categoria categoria;

    public Producto(){}

    public Producto(int idProducto, String portada, String nombreProducto, Double precio, int stock, Categoria categoria) {
        this.idProducto = idProducto;
        this.portada = portada;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", portada='" + portada + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", categoria=" + categoria +
                '}';
    }
}