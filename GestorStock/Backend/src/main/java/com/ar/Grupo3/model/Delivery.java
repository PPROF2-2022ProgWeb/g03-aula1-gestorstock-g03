package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

    private static final long serialVersionUID = -3141717393805881465L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Delivery")
    private Long idDelivery;

    @Column(name = "Nombre_Delivery")
    private String nombreDelivery;

    @Column(name = "Id_Pedido")
    private Long idPedido;

    @Column(name = "Fecha_Delivery")
    private Date fechaDelivery;

    @Column(name = "Observaciones")
    private String observaciones;

    public Delivery() {
        // Constructor Vacio
    }

    public Delivery(Long idDelivery) {
        super();
        this.idDelivery = idDelivery;
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

    @Override
    public int hashCode() {
        return Objects.hash(fechaDelivery, idDelivery, idPedido, nombreDelivery, observaciones);
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
        Delivery other = (Delivery) obj;
        return Objects.equals(fechaDelivery, other.fechaDelivery) && Objects.equals(idDelivery, other.idDelivery)
                && Objects.equals(idPedido, other.idPedido) && Objects.equals(nombreDelivery, other.nombreDelivery)
                && Objects.equals(observaciones, other.observaciones);
    }

    @Override
    public String toString() {
        return "Delivery [idDelivery=" + idDelivery + ", nombreDelivery=" + nombreDelivery + ", idPedido=" + idPedido
                + ", fechaDelivery=" + fechaDelivery + ", observaciones=" + observaciones + "]";
    }

}
