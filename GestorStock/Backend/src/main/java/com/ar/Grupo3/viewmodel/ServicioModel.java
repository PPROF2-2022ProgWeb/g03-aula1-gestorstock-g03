package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class ServicioModel implements Serializable {

    private static final long serialVersionUID = 8035456826789062090L;

    private Long idServicio;

    private String nombreServicio;

    private Long idFactura;

    private Double valor;

    private String descripcion;

    private String estado;

    // Not Mapped
    private String nombreFactura;

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

    public String getNombreFactura() {
        return nombreFactura;
    }

    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, estado, idFactura, idServicio, nombreFactura, nombreServicio, valor);
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
        ServicioModel other = (ServicioModel) obj;
        return Objects.equals(descripcion, other.descripcion) && Objects.equals(estado, other.estado)
                && Objects.equals(idFactura, other.idFactura) && Objects.equals(idServicio, other.idServicio)
                && Objects.equals(nombreFactura, other.nombreFactura)
                && Objects.equals(nombreServicio, other.nombreServicio) && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "ServicioModel [idServicio=" + idServicio + ", nombreServicio=" + nombreServicio + ", idFactura="
                + idFactura + ", valor=" + valor + ", descripcion=" + descripcion + ", estado=" + estado
                + ", nombreFactura=" + nombreFactura + "]";
    }

}
