package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class DeliveryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDelivery;

    private String nombreDelivery;

    private Long idPedido;

    private Date fechaDelivery;

    private String observaciones;

    // Not Mapped
    private String nombrePedido;

    public DeliveryModel() {
        // Constructor Vacio
    }

    // Getters and Setters
    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getNombreDelivery() {
        return nombreDelivery;
    }

    public void setNombreDelivery(String nombreDelivery) {
        this.nombreDelivery = nombreDelivery;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaDelivery() {
        return fechaDelivery;
    }

    public void setFechaDelivery(Date fechaDelivery) {
        this.fechaDelivery = fechaDelivery;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaDelivery, idDelivery, idPedido, nombreDelivery, nombrePedido, observaciones);
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
        DeliveryModel other = (DeliveryModel) obj;
        return Objects.equals(fechaDelivery, other.fechaDelivery) && Objects.equals(idDelivery, other.idDelivery)
                && Objects.equals(idPedido, other.idPedido) && Objects.equals(nombreDelivery, other.nombreDelivery)
                && Objects.equals(nombrePedido, other.nombrePedido)
                && Objects.equals(observaciones, other.observaciones);
    }

    @Override
    public String toString() {
        return "DeliveryModel [idDelivery=" + idDelivery + ", nombreDelivery=" + nombreDelivery + ", idPedido="
                + idPedido + ", fechaDelivery=" + fechaDelivery + ", observaciones=" + observaciones + ", nombrePedido="
                + nombrePedido + "]";
    }

}
