package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 3190825055807787283L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Pedido")
    private Long idPedido;

    @Column(name = "Nombre_Pedido")
    private String nombrePedido;

    @Column(name = "Id_Usuario")
    private Long idUsuario;

    @Column(name = "Id_Producto")
    private Long idProducto;

    @Column(name = "Id_Factura")
    private Long idFactura;

    @Column(name = "Valor")
    private Double valor;

    @Column(name = "Fecha_Pedido")
    private Date fechaPedido;

    @Column(name = "Observaciones")
    private String observaciones;

    public Pedido() {
        // Constructor vacio
    }

    public Pedido(Long idPedido) {
        super();
        this.idPedido = idPedido;
    }

    // Getters and setters
    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPedido, idFactura, idPedido, idProducto, idUsuario, nombrePedido, observaciones,
                valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pedido other = (Pedido) obj;
        return Objects.equals(fechaPedido, other.fechaPedido) && Objects.equals(idFactura, other.idFactura)
                && Objects.equals(idPedido, other.idPedido) && Objects.equals(idProducto, other.idProducto)
                && Objects.equals(idUsuario, other.idUsuario) && Objects.equals(nombrePedido, other.nombrePedido)
                && Objects.equals(observaciones, other.observaciones) && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", nombrePedido=" + nombrePedido + ", idUsuario=" + idUsuario
                + ", idProducto=" + idProducto + ", idFactura=" + idFactura + ", valor=" + valor + ", fechaPedido="
                + fechaPedido + ", observaciones=" + observaciones + "]";
    }
}
