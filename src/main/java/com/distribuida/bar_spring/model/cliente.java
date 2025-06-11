package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name="clientes")
@Data
public class cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_clientes")
    private int idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public cliente(){

    }

    public cliente(int idCliente, String nombre, String correo, String carrera, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", carrera='" + carrera + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
