package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 3642943674335204525L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Servicio")
    private Long idServicio;

    @Column(name = "Nombre_Servicio")
    private String nombreServicio;

    @Column(name = "Id_Factura")
    private Long idFactura;

    @Column(name = "Valor")
    private Double valor;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    public Servicio() {
        // Constructor Vacio
    }

    public Servicio(Long idServicio) {
        super();
        this.idServicio = idServicio;
    }

    // Getters and Setters
    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, estado, idFactura, idServicio, nombreServicio, valor);
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
        Servicio other = (Servicio) obj;
        return Objects.equals(descripcion, other.descripcion) && Objects.equals(estado, other.estado)
                && Objects.equals(idFactura, other.idFactura) && Objects.equals(idServicio, other.idServicio)
                && Objects.equals(nombreServicio, other.nombreServicio) && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "Servicio [idServicio=" + idServicio + ", nombreServicio=" + nombreServicio + ", idFactura=" + idFactura
                + ", valor=" + valor + ", descripcion=" + descripcion + ", estado=" + estado + "]";
    }

}
