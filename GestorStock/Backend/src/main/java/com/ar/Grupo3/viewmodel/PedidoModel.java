package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PedidoModel implements Serializable {

    private static final long serialVersionUID = -7667584928376374052L;

    private Long idPedido;

    private String nombrePedido;

    private Long idUsuario;

    private Long idProducto;

    private Long idFactura;

    private Double valor;

    private Date fechaPedido;

    private String observaciones;

    // Not Mapped
    private String nombreUsuario;

    private String nombreProducto;

    private String nombreFactura;

    public PedidoModel() {
        // Constructor vacio
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreFactura() {
        return nombreFactura;
    }

    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPedido, idFactura, idPedido, idProducto, idUsuario, nombreFactura, nombrePedido,
                nombreProducto, nombreUsuario, observaciones, valor);
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
        PedidoModel other = (PedidoModel) obj;
        return Objects.equals(fechaPedido, other.fechaPedido) && Objects.equals(idFactura, other.idFactura)
                && Objects.equals(idPedido, other.idPedido) && Objects.equals(idProducto, other.idProducto)
                && Objects.equals(idUsuario, other.idUsuario) && Objects.equals(nombreFactura, other.nombreFactura)
                && Objects.equals(nombrePedido, other.nombrePedido)
                && Objects.equals(nombreProducto, other.nombreProducto)
                && Objects.equals(nombreUsuario, other.nombreUsuario)
                && Objects.equals(observaciones, other.observaciones) && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "PedidoModel [idPedido=" + idPedido + ", nombrePedido=" + nombrePedido + ", idUsuario=" + idUsuario
                + ", idProducto=" + idProducto + ", idFactura=" + idFactura + ", valor=" + valor + ", fechaPedido="
                + fechaPedido + ", observaciones=" + observaciones + ", nombreUsuario=" + nombreUsuario
                + ", nombreProducto=" + nombreProducto + ", nombreFactura=" + nombreFactura + "]";
    }

}
