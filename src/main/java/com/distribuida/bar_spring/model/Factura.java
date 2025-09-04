package com.distribuida.bar_spring.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int idFactura;

    @Column(name = "num_factura", unique = true)
    private String numFactura;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total_neto")
    private Double totalNeto;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Factura() {}

    // Constructor, getters y setters
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public String getNumFactura() { return numFactura; }
    public void setNumFactura(String numFactura) { this.numFactura = numFactura; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Double getTotalNeto() { return totalNeto; }
    public void setTotalNeto(Double totalNeto) { this.totalNeto = totalNeto; }

    public Double getIva() { return iva; }
    public void setIva(Double iva) { this.iva = iva; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", numFactura='" + numFactura + '\'' +
                ", fecha=" + fecha +
                ", totalNeto=" + totalNeto +
                ", iva=" + iva +
                ", total=" + total +
                ", cliente=" + cliente +
                ", pedido=" + pedido +
                '}';
    }
}