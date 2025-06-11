package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="productos")
@Data
public class Producto{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id_producto")
    private int idProducto;
@Column(name="nombre_producto")
    private String nombreProducto;
@Column(name = "precio")
    private Double precio;
@Column(name="stock")
    private int stock;


    public Producto(){}

    public Producto(int idProducto, String nombreProducto, Double precio, int stock) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
