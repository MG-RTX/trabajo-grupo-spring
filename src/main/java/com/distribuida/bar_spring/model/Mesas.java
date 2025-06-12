package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="mesas")
@Data
public class Mesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private int idMesa;

    @Column(name = "numero_mesa")
    private int numeroMesa;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "ubicacion")
    private String ubicacion;

    public Mesas() {
    }

    public Mesas(int idMesa, int numeroMesa, int capacidad, String ubicacion) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Mesas{" +
                "idMesa=" + idMesa +
                ", numeroMesa=" + numeroMesa +
                ", capacidad=" + capacidad +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}


