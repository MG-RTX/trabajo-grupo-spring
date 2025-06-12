package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="promociones")
@Data
public class promociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_promocion")
    private int idPromocion;

    @Column(name="nombre_promocion")
    private String nombrepromocion;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="descuento")
    private Double descuento;

    @Column(name="fecha_inicio")
    private String fechaInicio;

    @Column(name="fecha_fin")
    private String fechaFin;

    @Column(name="activa")
    private String  activo;

    public promociones() {
    }

    public promociones(int idPromocion, String nombrepromocion, String descripcion, Double descuento, String fechaInicio, String fechaFin, String activo) {
        this.idPromocion = idPromocion;
        this.nombrepromocion = nombrepromocion;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrepromocion() {
        return nombrepromocion;
    }

    public void setNombrepromocion(String nombrepromocion) {
        this.nombrepromocion = nombrepromocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "promociones{" +
                "idPromocion=" + idPromocion +
                ", nombrepromocion='" + nombrepromocion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descuento=" + descuento +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}

