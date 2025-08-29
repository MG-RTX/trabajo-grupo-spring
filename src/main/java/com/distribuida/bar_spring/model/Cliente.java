package com.distribuida.bar_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {  // Cambiado a mayúscula
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_clientes")
    private int idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "cedula")
    private String cedula;  // Cambiado a String (más común para cédulas)

    public Cliente(){  // Cambiado a mayúscula

    }

    public Cliente(int idCliente, String nombre, String apellido, String correo, String cedula) {  // Cambiado a mayúscula y tipo
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.apellido = apellido;
        this.cedula = cedula;
    }

    // Getters y Setters (mantenidos igual pero con tipo cambiado para cédula)
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {  // Cambiado a String
        return cedula;
    }

    public void setCedula(String cedula) {  // Cambiado a String
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Cliente{" +  // Cambiado a mayúscula
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", cedula=" + cedula +
                '}';
    }
}